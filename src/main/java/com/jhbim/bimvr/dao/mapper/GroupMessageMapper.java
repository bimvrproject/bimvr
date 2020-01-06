package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    GroupMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);

    //推荐群根据热度显示
    List<GroupMessage> getAllheat();
    //查询未读消息条数0未读 1 已读
    List<GroupMessage> getusercount(@Param("userId") Integer userId, @Param("groupId") String groupId);
}