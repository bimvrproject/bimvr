package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MeterialFolder;

public interface MeterialFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeterialFolder record);

    int insertSelective(MeterialFolder record);

    MeterialFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeterialFolder record);

    int updateByPrimaryKey(MeterialFolder record);
}