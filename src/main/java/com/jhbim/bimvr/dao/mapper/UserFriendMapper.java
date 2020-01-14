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
    //全部忽略或全部同意
    int updateIslikeAll(@Param("userphone") String [] userphone,@Param("friendphone") String[] friendphone,@Param("islike") Integer islike);
    //根据当前登录人的手机号查询
    List<UserFriend> getuserphone(String phone);
    //查询共同好友排除自己
    List<UserFriend> getisnotuserphone(@Param("userphone") String userphone,@Param("friendphone") String friendphone);
    //根据islike 查询两者之间是不是好友  1同意 2拒绝 0搁置
    UserFriend getusephoneandfriendphone(@Param("userphone") String userphone,@Param("friendphone") String friendphone);
    //删除好友
    int deleteFriendphone(@Param("userphone")String userphone,@Param("friendphone") String friendphone);
    //阻止重复增加好友
    UserFriend getisnotexist(@Param("userphone") String userphone,@Param("friendphone") String friendphone,@Param("islike") Integer islike);
    //根据本人手机号查询好友的手机号
    List<UserFriend> getphone(String mephone);
    //邀请进群排除已是好友
    List<UserFriend> getnotfriendphone(@Param("userphone") String userphone,@Param("friendphone") List<String> friendphone,@Param("islike") Integer islike);
}