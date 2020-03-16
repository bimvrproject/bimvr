package com.jhbim.bimvr.dao.entity.vo;

import java.util.Date;

/**
 * 回复评论表Vo类
 */
public class ReplyVo {
    private String id; //回评id

    private String picture; //图片

    private String tousername;  //回复评论人的名称

    private Date time; //时间

    private String content;     //回复评论的内容

    private Integer state;  //0是别人发的评论 1是本人发的

    private Integer accountnum;  //点赞数

    private String userphone;   //回评人手机号

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

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(Integer accountnum) {
        this.accountnum = accountnum;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
