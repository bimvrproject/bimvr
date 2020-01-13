package com.jhbim.bimvr.controller.pc;

import com.jhbim.bimvr.dao.entity.pojo.*;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.entity.vo.UserVo;
import com.jhbim.bimvr.dao.mapper.*;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/${version}/pcUser")
public class UserController {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserFriendMapper userFriendMapper;
    /**
     *   根据token去获取用户信息
     * */
    @RequestMapping("/getUserByToken")
    public Result getUser(){
        User user = ShiroUtil.getUser();
        return  new Result(ResultStatusCode.OK,user);
    }

    /**
     * 查询用户表和角色表的字段  并存储到userVo实体类中
     * @param phone
     * @return
     */
    @GetMapping("/getphoneandrole/{phone}")
    public Result getphoneandrole(@PathVariable String phone){
        User user=userMapper.selectByPrimaryKey(phone);
        Role role=roleMapper.selectByPrimaryKey(user.getRoleId());
        List<UserFriend> userFriendList = userFriendMapper.getuserphone(phone);
        List<String> stringList = new ArrayList<>();
        for(UserFriend uf :userFriendList){
            stringList.add(uf.getFriendphone());
        }
        UserVo userVo=new UserVo();
        userVo.setPhone(user.getPhone());
        userVo.setPicture(user.getPricture());
        userVo.setRoleName(role.getRoleName());
        userVo.setImage(role.getImage());
        userVo.setUsername(user.getUserName());
        userVo.setPosotion(user.getPosotion());
        userVo.setCompanyname(user.getCompanyname());
        userVo.setRemarks(user.getRemarks());
        userVo.setFriendphone(stringList);
        return new Result(ResultStatusCode.UserVo,userVo);
    }

    /**
     * 修改用户昵称
     * @param userName  昵称
     * @param phone 手机号
     * @return
     */
    @GetMapping("/updatausername")
    public Result updatausername(String userName,String phone){
        userMapper.updatausername(userName,phone);
        return new Result(ResultStatusCode.OK,"昵称修改成功");
    }

    /**
     * 修改所属公司
     * @param companyname 所属公司
     * @param phone 手机号
     * @return
     */
    @GetMapping("/updatacompanyname")
    public Result updatacompanyname(String companyname,String phone){
        userMapper.updatacompanyname(companyname,phone);
        return new Result(ResultStatusCode.OK,"所属公司修改成功");
    }

    /**
     * 修改职位
     * @param position 职位
     * @param phone  手机号
     * @return
     */
    @GetMapping("/updataposition")
    public Result updataposition( String position,String phone){
        userMapper.updataposition(position,phone);
        return new Result(ResultStatusCode.OK,"职位修改成功");
    }

    /**
     * 修改备注
     * @param remarks 备注
     * @param phone 手机号
     * @return
     */
    @GetMapping("/updataremarks")
    public Result updataremarks(String remarks,String phone){
        userMapper.updataremarks(remarks,phone);
        return new Result(ResultStatusCode.OK,"备注修改成功");
    }

    /**
     * 查询当前用户的信息
     * @param phone
     * @return
     */
    @GetMapping("/getuserAll")
    public Result getuserAll( String phone){
        return new Result(ResultStatusCode.OK,userMapper.selectByPrimaryKey(phone));
    }

    /**
     * 根据用户手机号或昵称查询
     * @param phoneusername
     * @return
     */
    @RequestMapping("/findByuserphoneorusername")
    public Result findByuserphoneorusername(String phoneusername){
        if(phoneusername==null || phoneusername.equals("")){
            return new Result(ResultStatusCode.OK);
        }
        return new Result(ResultStatusCode.OK,userMapper.findByuserphoneorusername(phoneusername,phoneusername));
    }
}
