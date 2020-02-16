package com.jhbim.bimvr.dao.entity.vo;

public class UpperModelVo {
    private String modelid;  //模型id

    private String thumbs;  //缩略图

    private int upper;      //是否上架 0上架 1下架

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getThumbs() {
        return thumbs;
    }

    public void setThumbs(String thumbs) {
        this.thumbs = thumbs;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }
}
