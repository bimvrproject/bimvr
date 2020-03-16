package com.jhbim.bimvr.dao.entity.vo;

import java.util.Date;
import java.util.List;

/**
 * 评论表VO类
 */
public class CommentVo {
    private String id;      //编号

    private String picture; //图片

    private String name; //名称

    private Date time; //时间

    private String comment; //父级评论

    private List<ReplyVo> replyVos;  //存储子级评论

    private Integer state;  //0是别人发的评论 1是本人发的

    private Integer accountnum;

    public Integer getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(Integer accountnum) {
        this.accountnum = accountnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ReplyVo> getReplyVos() {
        return replyVos;
    }

    public void setReplyVos(List<ReplyVo> replyVos) {
        this.replyVos = replyVos;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
