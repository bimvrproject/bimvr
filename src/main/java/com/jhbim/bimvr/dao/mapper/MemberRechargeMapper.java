package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MemberRecharge;

public interface MemberRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberRecharge record);

    int insertSelective(MemberRecharge record);

    MemberRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberRecharge record);

    int updateByPrimaryKey(MemberRecharge record);
}