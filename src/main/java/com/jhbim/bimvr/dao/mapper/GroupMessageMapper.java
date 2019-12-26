package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupMessage;

public interface GroupMessageMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    GroupMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);
}