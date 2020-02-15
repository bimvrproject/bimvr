package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class ScUserModel implements Serializable {
    private Long id;

    private String userphone;

    private String onemenu;

    private String modelId;

    private String twomenu;

    private Integer price;

    private Integer num;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getOnemenu() {
        return onemenu;
    }

    public void setOnemenu(String onemenu) {
        this.onemenu = onemenu == null ? null : onemenu.trim();
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getTwomenu() {
        return twomenu;
    }

    public void setTwomenu(String twomenu) {
        this.twomenu = twomenu == null ? null : twomenu.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userphone=").append(userphone);
        sb.append(", onemenu=").append(onemenu);
        sb.append(", modelId=").append(modelId);
        sb.append(", twomenu=").append(twomenu);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", type=").append(type);
        sb.append("]");
        return sb.toString();
    }
}