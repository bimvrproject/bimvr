package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Zan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Zan record);

    int insertSelective(Zan record);

    Zan selectByPrimaryKey(Integer id);

    Zan select(@Param("workId")String workId, @Param("genre") Integer genre,@Param("userId") String userId);

    int updateByPrimaryKeySelective(Zan record);

    int updateByPrimaryKey(Zan record);

    List<Zan> list( @Param("list")List<String> list, @Param("genre") Integer genre,@Param("userId") String userId);
}