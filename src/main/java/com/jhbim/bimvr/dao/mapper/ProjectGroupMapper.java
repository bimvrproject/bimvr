package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ProjectGroup;

import java.util.List;

public interface ProjectGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectGroup record);

    int insertSelective(ProjectGroup record);

    ProjectGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectGroup record);

    int updateByPrimaryKey(ProjectGroup record);

    List<ProjectGroup> findByprojectid(String projectid);
}