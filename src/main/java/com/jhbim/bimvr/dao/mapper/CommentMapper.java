package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //根据模型id查询该模型评论条数
    int composeidnum(String composeid);

    //根据模型id查询该模型全部的评论 按照时间排序
    List<Comment> findBymodelid(String composeId);

    int updateaccountnum(Comment record);
}