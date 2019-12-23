package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Meterial;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 修改excel文件的名称
     * @param meterialName
     * @param id
     * @return
     */
    int Updateexcelname(@Param("meterialName") String meterialName, @Param("id") Integer id);

    /**
     * 根据创建时间修改文件夹的名称
     * @param foldername 文件夹
     * @param mtime 时间
     * @return
     */
    int meterialupdatefolder(@Param("foldername")String foldername,@Param("mtime")String mtime);
}