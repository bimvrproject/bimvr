package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.DisposeRvt;

public interface DisposeRvtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisposeRvt record);

    int insertSelective(DisposeRvt record);

    DisposeRvt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisposeRvt record);

    int updateByPrimaryKey(DisposeRvt record);
}