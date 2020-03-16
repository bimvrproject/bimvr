package com.jhbim.bimvr.dao.mapper;

import com.jhbim.bimvr.dao.entity.pojo.Reply;

import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    //根据父评论id查询子评论
    List<Reply> findByreplycommentid(String commentid);
    //根据父级评论id删除子级评论
    int deleteByCommentid(String id);

    int updateaccountnum(Reply reply);
}