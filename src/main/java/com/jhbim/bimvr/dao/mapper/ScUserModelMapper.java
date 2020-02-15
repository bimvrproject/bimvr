package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ScUserModel;

public interface ScUserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScUserModel record);

    int insertSelective(ScUserModel record);

    ScUserModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScUserModel record);

    int updateByPrimaryKey(ScUserModel record);
}