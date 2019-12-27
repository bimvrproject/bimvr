package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFriendMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserFriend record);

    int insertSelective(UserFriend record);

    UserFriend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserFriend record);

    int updateByPrimaryKey(UserFriend record);

    List<String> friendList(@Param("userphone") String userphone, @Param("islike") Integer islike);
    //根据是否是共同好友和手机号、类型查询
    List<UserFriend> findByIslikeanduserphoneandtype(@Param("islike") Integer islike,@Param("userphone") String userphone,@Param("type") Integer type);
    //修改islike为1 成为共同好友
    int updateByuserphoneandfriendphoneandislike(@Param("userphone") String userphone,@Param("friendphone") String friendphone,@Param("islike") Integer islike);
}