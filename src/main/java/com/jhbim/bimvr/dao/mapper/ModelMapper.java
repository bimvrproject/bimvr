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
    //根据模型(项目)id查询模型的点赞数
    int getmodelid(String projectid);
    //点赞数
    int updateAccount(Model model);
    //根据模型id查询
    Model selectbymodelid(String modelid);
}