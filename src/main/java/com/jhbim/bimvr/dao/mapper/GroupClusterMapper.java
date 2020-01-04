package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupClusterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupCluster record);

    int insertSelective(GroupCluster record);

    GroupCluster selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupCluster record);

    int updateByPrimaryKey(GroupCluster record);

    //根据所对应的群号查群
    GroupCluster findByGroupNo(String groupno);
    //根据群号和群昵称查询
    List<GroupCluster> findBygroupnoandgroupname(@Param("groupno") String groupno, @Param("groupname") String groupname);
}