package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Rvt;

public interface RvtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rvt record);

    int insertSelective(Rvt record);

    Rvt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rvt record);

    int updateByPrimaryKey(Rvt record);

    //根据项目id查询
    Rvt findByprojectid(String projectid);
}