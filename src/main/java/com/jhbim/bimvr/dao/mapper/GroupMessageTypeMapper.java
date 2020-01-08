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
    //推荐群根据热度显示
    List<GroupMessageType> getAllheat();
}