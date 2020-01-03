package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserFriend;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.UserFriendMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.IdWorker;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author shuihu
 * @create 2019-12-26 2:08 PM
 */

@RestController
@RequestMapping("/${version}/friend")
public class FriendController {


    @Autowired
    private UserFriendMapper userFriendMapper;

    @Autowired
    private UserMapper userMapper;
    @Resource
    IdWorker idWorker;

    /**
     *  获取好友信息列表
     * @return
     */
    @GetMapping("/friendList")
    public Result friendList(){
        User user = ShiroUtil.getUser();
       List<String> list = userFriendMapper.friendList(user.getPhone(),1);
        if (list.isEmpty()){
            return new Result(ResultStatusCode.OK);
        }
        Integer length = list.size();
        List<User> userList = userMapper.userList(list);
        Map<String,Object> map=new HashMap<>();
        map.put("data",userList);
        map.put("total",length);
        return new Result(ResultStatusCode.SUCCESS,map);
    }

    /**
     *  增加好友记录
     * @param userphone 本人手机号
     * @param friendphone 好友手机号
     * @return
     */
    @RequestMapping("/AddUserFriend")
    public Result AddUserFriend(String userphone,String friendphone,String message){
        //请求者
        UserFriend userFriend=new UserFriend();
        userFriend.setId(idWorker.nextId()+"");
        userFriend.setUserphone(userphone);
        userFriend.setFriendphone(friendphone);
        userFriend.setType(0);
        userFriendMapper.insertSelective(userFriend);
        //接收者
        UserFriend uf=new UserFriend();
        uf.setId(idWorker.nextId()+"");
        uf.setUserphone(friendphone);
        uf.setFriendphone(userphone);
        uf.setType(1);
        uf.setMessage(message);
        userFriendMapper.insertSelective(uf);

        return new Result(ResultStatusCode.OK,"好友请求发送成功...");
    }

    /**
     * 根据是否是共同好友和手机号、类型查询  登录上去显示增加你的好友信息 请求者与被请求者
     * @param userphone 手机号
     * @return
     */
    @RequestMapping("/findByIslikeanduserphoneandtype")
    public Result findByIslikeanduserphoneandtype(String userphone){
        if(userphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        Map<String,Object> map = new HashMap<>();
        List<UserFriend> userFriendList = userFriendMapper.findByIslikeanduserphoneandtype(0,userphone,1);
        map.put("count",userFriendList.size());
        map.put("data",userFriendList);
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     *  好友增加成功
     * @param userphone
     * @param friendphone
     * @return
     */
    @RequestMapping("/updateuserfriend")
    public Result updateByuserphoneandfriendphoneandislike(String userphone,String friendphone){
        if(userphone.isEmpty() || friendphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        userFriendMapper.updateByuserphoneandfriendphoneandislike(userphone,friendphone,1);
        userFriendMapper.updateByuserphoneandfriendphoneandislike(friendphone,userphone,1);
        return new Result(ResultStatusCode.OK,"好友增加成功...");
    }

    /**
     * 好友拒绝(忽略好友)
     * @param userphone
     * @param friendphone
     * @return
     */
    @RequestMapping("/Refusedtofriend")
    public Result Refusedtofriend(String userphone,String friendphone){
        if(userphone.isEmpty() || friendphone.isEmpty()){
            return new Result(ResultStatusCode.BAD_REQUEST);
        }
        userFriendMapper.updateByuserphoneandfriendphoneandislike(userphone,friendphone,2);
        userFriendMapper.updateByuserphoneandfriendphoneandislike(friendphone,userphone,2);
        return new Result(ResultStatusCode.OK,"忽略成功...");
    }

    /**
     * 查询好友右侧默认显示
     *  1.根据有共同好友查询
     *  2.随机显示
     * @return
     */
    @RequestMapping("/Interests")
    public Result Interests(){
        Map<String,Object> map = new HashMap<>();
        User user = ShiroUtil.getUser();
        //查询本人是否有好友
        List<UserFriend> userFriend = userFriendMapper.getuserphone(user.getPhone());
        if(userFriend.size()==0){  //没有
            //随机查询User表
            List<User> userList = userMapper.randomgetAll();
            map.put("data",userList);
//            System.out.println("空");
        }else{  //有
//            System.out.println("非空");
            //查询本人的好友
            List<User> getAlllist = new ArrayList();
//            System.out.println("*********全部好友**********");
            //全部的用户
            List all=new ArrayList();
            List<User> userList = userMapper.randomgetAllisnotself(user.getPhone());
            //所有用户
            for (int i = 0;i<userList.size();i++){
                all.add(userList.get(i).getPhone());
//                System.out.println(userList.get(i).getPhone());
            }
//            System.out.println("********共同好友***********");
            //好友
            List friendlist=new ArrayList();
            List<UserFriend> userFriendList = userFriendMapper.getuserphone(user.getPhone());
            for (UserFriend uf: userFriendList) {
                friendlist.add(uf.getFriendphone());
//                System.out.println(uf.getFriendphone());
            }
//            System.out.println("-----不同好友------");
            //差集
            all.removeAll(friendlist);
            List num=new ArrayList();
            Iterator<String> it=all.iterator();
            while (it.hasNext()) {
                num.add(it.next());
            }
            for (Object o:num){
//                System.out.println(o);
                User user1= userMapper.selectByPrimaryKey(o.toString());
                getAlllist.add(user1);
            }

            map.put("data",getAlllist);
        }
        return new Result(ResultStatusCode.OK,map);
    }

    /**
     * 根据islike 查询两者之间是不是好友  1同意 2拒绝 0搁置
     * @param userphone 本人手机号
     * @param friendphone 好友手机号
     * @return
     */
    @RequestMapping("/getusephoneandfriendphone")
    public Result getusephoneandfriendphone(String userphone,String friendphone){
        return new Result(ResultStatusCode.OK,userFriendMapper.getusephoneandfriendphone(userphone,friendphone));
    }
}
