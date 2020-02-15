package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ScUserModelNum;

public interface ScUserModelNumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScUserModelNum record);

    int insertSelective(ScUserModelNum record);

    ScUserModelNum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScUserModelNum record);

    int updateByPrimaryKey(ScUserModelNum record);

    //查询用户上架的模型数量
    int getcount(String userphone);
}