package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupMessageType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GroupMessageTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupMessageType record);

    int insertSelective(GroupMessageType record);

    GroupMessageType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupMessageType record);

    int updateByPrimaryKey(GroupMessageType record);

    //始终查询用户最后一次离开的时间
    List<GroupMessageType> findbyuserphone(@Param("toUser") String toUser,@Param("groupno") String groupno);
   //是否已读
    List<GroupMessageType> getusercount(@Param("toUser") String toUser,@Param("groupno") String groupno,@Param("toTime") String toTime);
    //根据是否推荐群的热度查询
    List<GroupMessageType> getAllheat();
    //修改最后一次离开时间
    int updatefromtime(@Param("fromTime") String fromTime,@Param("groupno") String groupno,@Param("toUser") String toUser,@Param("toTime") String toTime);
    //根据群号查询聊天内容
    List<GroupMessageType> findbygroupno(String groupno);
    //退群之后删除本人在群里的内容
    int deletemsgid(@Param("toUser") String toUser,@Param("groupno") String groupno);
    //解散群组
    int deletegrouptype(String groupno);
}