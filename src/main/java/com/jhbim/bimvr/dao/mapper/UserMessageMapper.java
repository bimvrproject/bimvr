package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);

    List<UserMessage> messaheList(@Param("hairuserPhone") String hairuserPhone, @Param("closeduserPhone")String closeduserPhone );

    int updateMessage(@Param("stauts")Integer stauts,@Param("hairuserPhone") String hairuserPhone, @Param("closeduserPhone")String closeduserPhone);

    //获取未读消息条数
    int unreadcount(@Param("hairuserPhone") String hairuserPhone, @Param("closeduserPhone")String closeduserPhone);

    //查询所有人给该手机号发送的未读消息
    List<UserMessage> findByCloseduserphone(String phone);
}