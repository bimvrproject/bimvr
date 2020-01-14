package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Model;

import java.util.List;

public interface ModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    int insertSelective(Model record);

    Model selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Model record);

    int updateByPrimaryKey(Model record);

    List<Model> listRank();

    List<String> modelList();
}