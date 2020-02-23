package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.BuyModelRecord;

public interface BuyModelRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyModelRecord record);

    int insertSelective(BuyModelRecord record);

    BuyModelRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyModelRecord record);

    int updateByPrimaryKey(BuyModelRecord record);
}