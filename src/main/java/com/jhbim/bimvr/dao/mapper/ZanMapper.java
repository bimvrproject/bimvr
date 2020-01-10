package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Zan;

public interface ZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zan record);

    int insertSelective(Zan record);

    Zan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Zan record);

    int updateByPrimaryKey(Zan record);
}