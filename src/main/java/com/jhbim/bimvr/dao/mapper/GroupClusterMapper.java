package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;

public interface GroupClusterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupCluster record);

    int insertSelective(GroupCluster record);

    GroupCluster selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupCluster record);

    int updateByPrimaryKey(GroupCluster record);
}