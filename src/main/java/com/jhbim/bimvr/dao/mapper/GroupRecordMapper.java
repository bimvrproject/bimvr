package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupRecord;

public interface GroupRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupRecord record);

    int insertSelective(GroupRecord record);

    GroupRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupRecord record);

    int updateByPrimaryKey(GroupRecord record);
}