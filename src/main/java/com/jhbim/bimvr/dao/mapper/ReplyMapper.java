package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Reply;

public interface ReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}