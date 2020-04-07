package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.PlaceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceModelMapper {
    int deleteByPrimaryKey(String id);

    int insert(PlaceModel record);

    int insertSelective(PlaceModel record);

    PlaceModel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PlaceModel record);

    int updateByPrimaryKey(PlaceModel record);

    //根据地块名称查询
    PlaceModel selectByplotname(@Param("plotname") String plotname);

    //查询一次修改热度
    int updateheat(PlaceModel record);

    //查询本人的地块
    List<PlaceModel> selectByUserphone(@Param("usephone") String usephone);

    //查看本人已创建几个地块
    int count(@Param("usephone") String usephone);

    //根据热度查询
    List<PlaceModel> getAllHeat();

    //根据id更换模型
    int updatemodel(PlaceModel record);

    //根据模型id查询
    PlaceModel selectmodelid(@Param("modelid") String modelid);

    //根据id和地块模型查询
    PlaceModel findByidandplotname(PlaceModel record);

    //根据id、大陆名称、以及地块名称查询
    PlaceModel findByidandmainlandnameandplotname(PlaceModel record);

    //根据模型id查询
    PlaceModel findnbymodelid(String modelid);
    //排除24小时之内点赞的 其他的随机展示
    List<PlaceModel> randthumbup(List<String> list);

    List<PlaceModel> findBymodelidAll(String modelid);
}