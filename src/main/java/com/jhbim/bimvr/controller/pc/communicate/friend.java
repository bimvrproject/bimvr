package com.jhbim.bimvr.controller.pc.communicate;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.entity.pojo.UserFriend;
import com.jhbim.bimvr.dao.entity.vo.Result;
import com.jhbim.bimvr.dao.mapper.UserFriendMapper;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.system.enums.ResultStatusCode;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuihu
 * @create 2019-12-26 2:08 PM
 */

@RestController
@RequestMapping("/${version}/friend")
public class friend {


    @Autowired
    private UserFriendMapper userFriendMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *  获取好友信息列表
     * @return
     */
    @GetMapping("/friendList")
    public Result friendList(){
        User user = ShiroUtil.getUser();
        ArrayList<String> list = userFriendMapper.friendList(user.getPhone(),1);
        List<User> userList =  userMapper.userList(list);
        return new Result(ResultStatusCode.SUCCESS,userList);
    }
}
