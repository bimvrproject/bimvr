package com.jhbim.bimvr.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.jhbim.bimvr.dao.entity.pojo.MemberEnd;
import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserWallet;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.MemberEndMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.dao.mapper.UserWalletMapper;
import com.jhbim.bimvr.service.IUserService;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.system.shiro.LoginType;
import com.jhbim.bimvr.utils.AlipaySmsUtils;
import com.jhbim.bimvr.utils.SMSConfig;
import com.jhbim.bimvr.system.shiro.UserToken;
import com.jhbim.bimvr.utils.MD5Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/${version}/user")
public class LoginController {
    @Resource
    IUserService userService;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    UserMapper userMapper;
    @Resource
    UserWalletMapper userWalletMapper;
    @Value("${version}")
    private volatile  String version;
    @Resource
    MemberEndMapper memberEndMapper;
    /**
     * 用户密码登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password,HttpServletRequest request){
        //根据登录人的手机号查询他的所有的信息
        User user = userMapper.selectByPrimaryKey(username);
        //判断是否注册过
        if(user==null){
            return new Result(ResultStatusCode.IS_NOT_EXIST); //登录失败
        }
        //登录成功
        ServletContext application=request.getSession().getServletContext();
        application.setAttribute("User_Phone",user.getPhone());
        //注册的时候将密码加密  登录的时候跟MD5做匹配  登录成功
        if(MD5Util.encrypt(password).equals(user.getPassword())){
            if(user.getState()==0) {
                User updatestate = new User();
                updatestate.setState(1);
                updatestate.setPhone(username);
                userMapper.updatestate(updatestate);
            }
            SecurityUtils.getSubject().getSession().setTimeout(-1000L);
            UserToken token = new UserToken(LoginType.USER_PASSWORD, username, password);
            return shiroLogin(token);
        }
        return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
    }

    /**
     * 手机发送验证码
     *      注：由于是demo演示，此处不添加发送验证码方法；
     *          正常操作：发送验证码至手机并且将验证码存放在redis中，登录的时候比较用户穿过来的验证码和redis中存放的验证码
     * @param
     * @param
     * @return
     */
    @RequestMapping("/sendSmscode")
    public String sendSms(String mobile){
        String random= RandomStringUtils.randomNumeric(6);
        System.out.println(mobile+"随机数:"+random);
        //1分钟过期
        redisTemplate.opsForValue().set(mobile,random+"",1, TimeUnit.MINUTES);
        try {
            AlipaySmsUtils.sendSms(mobile,random);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "已发送...";
    }


    /**
     * 微信登录
     *      注：由于是demo演示，此处只接收一个code参数（微信会生成一个code，然后通过code获取openid等信息）
     *          其他根据个人实际情况添加参数
     * @param code
     * @return
     */
    @RequestMapping("wechatLogin")
    public Result wechatLogin(String code){
        // 此处假装code分别是username、password
        UserToken token = new UserToken(LoginType.WECHAT_LOGIN, code, code, code);
        return shiroLogin(token);
    }

    public Result shiroLogin(UserToken token){
        try {
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                JSON json = new JSONObject();
                ((JSONObject) json).put("token", subject.getSession().getId());

                return new Result(ResultStatusCode.OK, json);
            }else{
                return new Result(ResultStatusCode.SHIRO_ERROR);
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
            e.printStackTrace();
            return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }catch (LockedAccountException e){
            return new Result(ResultStatusCode.USER_FROZEN);
        }catch (Exception e){
            return new Result(ResultStatusCode.SYSTEM_ERR);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return new Result(ResultStatusCode.OK);
    }

    /**
     * 用户修改密码
     */
    @RequestMapping("/changePwd")
    public Result changePwd(String oldPwd, String newPwd) {
        int result = userService.updatePwd(oldPwd, newPwd);
        if (result==1) {
            return  new Result(ResultStatusCode.OK);
        }else {
            return new Result(ResultStatusCode.FAIL);
        }
    }

    /**
     *  判断手机号是否存在
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/register")
    public Result register(String phone){
        User user = userMapper.selectByPrimaryKey(phone);
        if(user!=null){
            return new Result(ResultStatusCode.FAIL, "已注册");
        }
        return new Result(ResultStatusCode.NoRegiter);
    }

    /**
     *  注册获取redis里面的验证码 判断是否一致 完成用户注册
     * @param smsCode  验证码
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/RegistercheckSmsCode")
    public Result checkSmsCode(String smsCode, String phone,String pwd){
        Result result = new Result();
        if(redisTemplate.opsForValue().get(phone)==null){
            result.setCode(1);
            result.setMsg("短信验证码输入超时!");
        }else{
            String code = redisTemplate.opsForValue().get(phone).toString();
            if(!code.equals(smsCode)){
                result.setCode(2);
                result.setMsg("短信验证码错误!");
            }else{
                User user=new User();
                user.setRoleId(3);
                user.setPhone(phone);
                String regex = "[a-z0-9A-Z]+$";
                if(pwd.matches(regex)==false){
                    result.setCode(6);
                    result.setMsg("不合法的字符");
                    return result;
                }else{
//                    user.setUserName(phone);
                    user.setUserName("此用户无昵称");
                    user.setPassword(MD5Util.encrypt(pwd));
                    user.setCreateTime(new Date());
                    user.setPricture("http://36.112.65.110:8080/picture/peoplepicture.png");
                   int i = userMapper.insertSelective(user);
                   if(i>0){
                       //用户钱包
                       UserWallet userWallet = new UserWallet();
                       userWallet.setUserphone(phone);
                       userWalletMapper.insertSelective(userWallet);
                       User user1 = userMapper.selectByPrimaryKey(phone);
                       MemberEnd memberEnd = new MemberEnd();
                       memberEnd.setUserId(user1.getUserId());
                       memberEnd.setRoleId(user1.getRoleId());
                       Calendar cal = Calendar.getInstance();
                       //新用户首次注册免费使用15天
                       cal.add(Calendar.DATE, 15);
                       Date date = cal.getTime();
                       memberEnd.setEndtime(date);
                       memberEndMapper.insertSelective(memberEnd);
                   }
                }
                return new Result(ResultStatusCode.RegiterSuccess);
            }
        }
        return result;
    }
    /**
     *  登录获取redis里面的验证码 判断是否一致 完成用户登录
     * @param smsCode 验证码
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/LogincheckSmsCode")
    public Result LogincheckSmsCode(String smsCode, String phone){
        Result result = new Result();
        if(redisTemplate.opsForValue().get(phone)==null){
            result.setCode(1);
            result.setMsg("短信验证码输入超时!");
        }else{
            String code = redisTemplate.opsForValue().get(phone).toString();
            if(!code.equals(smsCode)){
                result.setCode(2);
                result.setMsg("短信验证码错误!");
            }else{
                result.setCode(0);
                result.setMsg("成功");
                User updatestate=new User();
                updatestate.setState(1);
                updatestate.setPhone(phone);
                userMapper.updatestate(updatestate);
                SecurityUtils.getSubject().getSession().setTimeout(-1000L);
                UserToken token = new UserToken(LoginType.USER_PHONE, phone, smsCode);
                return shiroLogin(token);
            }
        }
        return result;
    }

    /**
     * 获取版本号
     * @return
     */
    @RequestMapping("/version")
    public Result versoin(){
        return new Result(ResultStatusCode.OK,version);
    }
}
