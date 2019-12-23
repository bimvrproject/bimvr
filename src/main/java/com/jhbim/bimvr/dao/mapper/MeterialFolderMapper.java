package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MeterialFolder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MeterialFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeterialFolder record);

    int insertSelective(MeterialFolder record);

    MeterialFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeterialFolder record);

    int updateByPrimaryKey(MeterialFolder record);

    /**
     * 根据日期和文件夹名称查询文件夹下面的文件
     * @param mftime
     * @param foldername
     * @return
     */
    List<MeterialFolder> listAll(@Param("mftime") Date mftime, @Param("foldername") String foldername);
}