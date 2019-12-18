package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Email;

public interface EmailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Email record);

    int insertSelective(Email record);

    Email selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Email record);

    int updateByPrimaryKey(Email record);
}