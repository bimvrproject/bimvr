package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Email;

public interface EmailMapper {
    int insert(Email record);

    int insertSelective(Email record);
}