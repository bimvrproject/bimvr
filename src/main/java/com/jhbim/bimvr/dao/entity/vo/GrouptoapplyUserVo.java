package com.jhbim.bimvr.dao.entity.vo;
//申请进群Vo类
public class GrouptoapplyUserVo {
    private String picture;
    private String username;
    private String message;
    private String roleimg;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getRoleimg() {
        return roleimg;
    }

    public void setRoleimg(String roleimg) {
        this.roleimg = roleimg;
    }
}
