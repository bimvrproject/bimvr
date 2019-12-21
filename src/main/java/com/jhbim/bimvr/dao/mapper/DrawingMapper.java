package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Drawing;

import java.util.List;

public interface DrawingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Drawing record);

    int insertSelective(Drawing record);

    Drawing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Drawing record);

    int updateByPrimaryKey(Drawing record);

    /**
     * 查询该项目下的图纸
     * @param projectid
     * @return
     */
    List<Drawing> getprojectdrawing(Integer projectid);
}