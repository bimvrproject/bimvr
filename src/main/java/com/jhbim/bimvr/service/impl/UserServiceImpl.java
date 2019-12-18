package com.jhbim.bimvr.service.impl;

import com.jhbim.bimvr.dao.entity.pojo.User;
import com.jhbim.bimvr.dao.mapper.UserMapper;
import com.jhbim.bimvr.service.IUserService;
import com.jhbim.bimvr.utils.MD5Util;
import com.jhbim.bimvr.utils.ShiroUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public User getByPhone(String phone) {
        return userMapper.selectByPrimaryKey(phone);
    }

    @Override
    public int updatePwd(String oldPwd, String newPwd) {

        int result = 0;

        String username = ShiroUtil.getUserName();
        String password = ShiroUtil.getPassword();
        oldPwd = MD5Util.encrypt(oldPwd);

        if (password.equals(oldPwd)) {
            User user = new User();
            user.setUserName(username);
            user.setPassword(MD5Util.encrypt(newPwd));
            result = userMapper.updatePwd(user);
        }

        return result;
    }

    @Override
    public int register(String phone, String password) {
       User returnu =  userMapper.selectByPrimaryKey(phone);
       if (returnu==null){
           User user = new User();
           user.setUserName(phone);
           user.setPassword(MD5Util.encrypt(password));
           int insert = userMapper.insert(user);
           return insert;
       }else{
           return 0;
       }
    }
}
