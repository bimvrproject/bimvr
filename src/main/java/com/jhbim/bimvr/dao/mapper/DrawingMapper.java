package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Drawing;
import org.apache.ibatis.annotations.Param;

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
    List<Drawing> getprojectdrawing(String projectid);

    /**
     * 拖拽图纸批量修改type
     * @param ids
     * @return
     */
    int drawingbatchUpdate(Integer[] ids);

    /**
     * 修改图纸的名称
     * @param drawName
     * @param id
     * @return
     */
    int updateDrawingname(@Param("drawName") String drawName, @Param("id") Integer id);

    /**
     * 根据创建时间修改文件夹的名称
     * @param dtime 时间
     * @param foldername 文件夹
     * @return
     */
    int updatedDrawingFolder(@Param("dtime") String dtime, @Param("foldername") String foldername);
}