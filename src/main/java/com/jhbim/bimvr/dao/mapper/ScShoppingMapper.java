package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ScShopping;

import java.util.List;

public interface ScShoppingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScShopping record);

    int insertSelective(ScShopping record);

    ScShopping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScShopping record);

    int updateByPrimaryKey(ScShopping record);

    //条件查询时间降序
    List<ScShopping> getlisttimer(ScShopping scShopping);
    //条件查询点赞降序
    List<ScShopping> getlistthumbsnumdesc(ScShopping scShopping);
    //条件查询点赞升序
    List<ScShopping> getlistthumbsnum(ScShopping scShopping);
}