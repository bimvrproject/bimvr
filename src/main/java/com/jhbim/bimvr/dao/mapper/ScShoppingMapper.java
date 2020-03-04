package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.ScShopping;

import java.math.BigDecimal;
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
    //当二级菜单为“全部”时，按照一级菜单和上架时间查询倒叙
    List<ScShopping> getlisttimerdesc(ScShopping scShopping);
    //按照一级菜单二级菜单和点赞数倒叙查询
    List<ScShopping> getlistthumbsnumdesc(ScShopping scShopping);
    //当二级菜单为“全部”时，按照一级菜单和点赞数倒叙查询
    List<ScShopping> getlistonemenuthumbsnumdesc(ScShopping scShopping);
    //按照一级菜单二级菜单和点赞数升序查询
    List<ScShopping> getlistthumbsnum(ScShopping scShopping);
    //当二级菜单为“全部”时，按照一级菜单和点赞数升序查询
    List<ScShopping> getlistonemenuthumbsnum(ScShopping scShopping);
    //模型下架删除该模型在商品表里的数据
    int modellowerdelete(String modelid);

    int updatethumbsnum(ScShopping scShopping);
    //根据模型id查询价格
    BigDecimal selectmodelid(String modelid);

}