package com.jhbim.bimvr.dao.entity.vo;

public class ShoppingVo {

    private String thumbnail; //缩略图
    private int price;  //价格
    private String modelid;  //模型id
    private int thumbsnum;//点赞数量

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
