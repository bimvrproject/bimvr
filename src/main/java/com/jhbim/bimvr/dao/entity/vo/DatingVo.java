package com.jhbim.bimvr.dao.entity.vo;

public class DatingVo {
    private String id;   //模型id
    private String plotname;  //地块名称
    private String username;       //用户昵称
    private String picture;     //用户头像
    private Integer account;    //点赞数量
    private String proimg;      //模型缩略图
    private String phone;       //用户手机号
    private String mainlandname;    //大陆名称
    private String plotid;      //地块id

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlotname() {
        return plotname;
    }

    public void setPlotname(String plotname) {
        this.plotname = plotname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getProimg() {
        return proimg;
    }

    public void setProimg(String proimg) {
        this.proimg = proimg;
    }

    public String getMainlandname() {
        return mainlandname;
    }

    public void setMainlandname(String mainlandname) {
        this.mainlandname = mainlandname;
    }

    public String getPlotid() {
        return plotid;
    }

    public void setPlotid(String plotid) {
        this.plotid = plotid;
    }
}
