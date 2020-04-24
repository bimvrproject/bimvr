package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Rvt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RvtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rvt record);

    int insertSelective(Rvt record);

    Rvt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rvt record);

    int updateByPrimaryKey(Rvt record);

    //根据项目id查询
    Rvt findByprojectid(String projectid);
    //根据模型是否处理状态查询
    List<Rvt> findAll(@Param("url") String url);
    //根据项目id修改状态
    int updatervttype(Rvt record);
}