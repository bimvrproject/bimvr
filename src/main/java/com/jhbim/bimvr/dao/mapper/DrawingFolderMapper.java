package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.DrawingFolder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrawingFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DrawingFolder record);

    int insertSelective(DrawingFolder record);

    DrawingFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DrawingFolder record);

    int updateByPrimaryKey(DrawingFolder record);

    /**
     * 根据日期和文件夹名称查询文件夹下面的文件
     * @param dftime  时间
     * @param foldername 文件夹
     * @return
     */
    List<DrawingFolder> DrawinglistAll(@Param("dftime") String dftime,@Param("foldername")String foldername);

    /**
     * 根据创建时间修改文件夹的名称
     * @param dftime 时间
     * @param foldername 文件夹
     * @return
     */
    int drawingupdatefolder(@Param("dftime") String dftime,@Param("foldername")String foldername);
}