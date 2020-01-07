package com.jhbim.bimvr.dao.entity.vo;

public class GroupMessageVo {

    //头像
    private String picture;
    //昵称
    private String username;
    //内容
    private String message;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
