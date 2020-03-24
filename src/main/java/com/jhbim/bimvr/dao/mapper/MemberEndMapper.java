package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.MemberEnd;

public interface MemberEndMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberEnd record);

    int insertSelective(MemberEnd record);

    MemberEnd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberEnd record);

    int updateByPrimaryKey(MemberEnd record);

    //根据用户编号查询
    MemberEnd selectbyuserid(Integer userid);
}