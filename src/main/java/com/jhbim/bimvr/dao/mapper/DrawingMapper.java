package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Drawing;

public interface DrawingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Drawing record);

    int insertSelective(Drawing record);

    Drawing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Drawing record);

    int updateByPrimaryKey(Drawing record);
}