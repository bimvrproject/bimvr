package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ScShopping implements Serializable {
    private Long id;

    private String modelId;

    private String onemenu;

    private String twomenu;

    private BigDecimal price;

    private Date timer;

    private Integer thumbsnum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getOnemenu() {
        return onemenu;
    }

    public void setOnemenu(String onemenu) {
        this.onemenu = onemenu == null ? null : onemenu.trim();
    }

    public String getTwomenu() {
        return twomenu;
    }

    public void setTwomenu(String twomenu) {
        this.twomenu = twomenu == null ? null : twomenu.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTimer() {
        return timer;
    }

    public void setTimer(Date timer) {
        this.timer = timer;
    }

    public Integer getThumbsnum() {
        return thumbsnum;
    }

    public void setThumbsnum(Integer thumbsnum) {
        this.thumbsnum = thumbsnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modelId=").append(modelId);
        sb.append(", onemenu=").append(onemenu);
        sb.append(", twomenu=").append(twomenu);
        sb.append(", price=").append(price);
        sb.append(", timer=").append(timer);
        sb.append(", thumbsnum=").append(thumbsnum);
        sb.append("]");
        return sb.toString();
    }
}