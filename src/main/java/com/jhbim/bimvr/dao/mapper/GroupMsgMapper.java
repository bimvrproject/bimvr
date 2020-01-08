package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.GroupMsg;

public interface GroupMsgMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(GroupMsg record);

    int insertSelective(GroupMsg record);

    GroupMsg selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(GroupMsg record);

    int updateByPrimaryKey(GroupMsg record);
}