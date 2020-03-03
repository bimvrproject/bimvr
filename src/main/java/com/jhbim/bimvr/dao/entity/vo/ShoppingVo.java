package com.jhbim.bimvr.dao.entity.vo;

import java.math.BigDecimal;

public class ShoppingVo {

    private String thumbnail; //缩略图
    private BigDecimal price;  //价格
    private String modelid;  //模型id
    private int thumbsnum;//点赞数量
    private String userphone; //用户手机号
    private String projectname; //项目名称

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public int getThumbsnum() {
        return thumbsnum;
    }

    public void setThumbsnum(int thumbsnum) {
        this.thumbsnum = thumbsnum;
    }
}
