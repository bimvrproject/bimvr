package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.DrawingFolder;

public interface DrawingFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DrawingFolder record);

    int insertSelective(DrawingFolder record);

    DrawingFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DrawingFolder record);

    int updateByPrimaryKey(DrawingFolder record);
}