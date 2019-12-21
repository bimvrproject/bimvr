package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Meterial;

import java.util.List;

public interface MeterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meterial record);

    int insertSelective(Meterial record);

    Meterial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meterial record);

    int updateByPrimaryKey(Meterial record);

    /**
     * 根据项目id展示相对应的excel清单表格
     * @param projectid
     * @return
     */
    List<Meterial> getprojectid(String projectid);

    /**
     * 拖拽清单批量修改type
     * @param ids
     * @return
     */
    int batchUpdate(Integer[] ids);
}