package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ProjectGroup;

public interface ProjectGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectGroup record);

    int insertSelective(ProjectGroup record);

    ProjectGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectGroup record);

    int updateByPrimaryKey(ProjectGroup record);

    ProjectGroup findByprojectid(String projectid);

}