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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<User> userList = userMapper.userList(list);
        return new Result(ResultStatusCode.SUCCESS,userList);
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
     * 根据是否是共同好友和手机号、类型查询
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
}
