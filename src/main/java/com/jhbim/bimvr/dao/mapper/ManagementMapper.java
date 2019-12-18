package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Management;

public interface ManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Management record);

    int insertSelective(Management record);

    Management selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Management record);

    int updateByPrimaryKey(Management record);
}