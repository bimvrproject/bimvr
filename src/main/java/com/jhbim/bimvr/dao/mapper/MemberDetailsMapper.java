package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MemberDetails;

public interface MemberDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberDetails record);

    int insertSelective(MemberDetails record);

    MemberDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberDetails record);

    int updateByPrimaryKey(MemberDetails record);

    int updateByPrimaryKeyByid(String id);
}