package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserFriend;

import java.util.ArrayList;
import java.util.List;

public interface UserFriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    ArrayList<String> friendList(String userphone, Integer islike);
}