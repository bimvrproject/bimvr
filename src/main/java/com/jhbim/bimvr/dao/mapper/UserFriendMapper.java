package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserFriend;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface UserFriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    List<String> friendList(@Param("userphone") String userphone, @Param("islike") Integer islike);
}