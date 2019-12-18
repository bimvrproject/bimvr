package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Dictionaries;

import java.util.List;

public interface DictionariesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dictionaries record);

    int insertSelective(Dictionaries record);

    Dictionaries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dictionaries record);

    int updateByPrimaryKey(Dictionaries record);

    /**
     * 查询父级权限
     * @return
     */
    List<Dictionaries> selectByparentid();

    /**
     * 查询子权限
     * @param parentid
     * @return
     */
    List<Dictionaries> selectByparentidchildren(Integer parentid);
}