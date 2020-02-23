package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BuyModelRecord implements Serializable {
    private Long id;

    private String selluserphone;

    private String buyuserphone;

    private String modelid;

    private BigDecimal price;

    private Date buttime;

    private Integer payWay;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelluserphone() {
        return selluserphone;
    }

    public void setSelluserphone(String selluserphone) {
        this.selluserphone = selluserphone == null ? null : selluserphone.trim();
    }

    public String getBuyuserphone() {
        return buyuserphone;
    }

    public void setBuyuserphone(String buyuserphone) {
        this.buyuserphone = buyuserphone == null ? null : buyuserphone.trim();
    }

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid == null ? null : modelid.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getButtime() {
        return buttime;
    }

    public void setButtime(Date buttime) {
        this.buttime = buttime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", selluserphone=").append(selluserphone);
        sb.append(", buyuserphone=").append(buyuserphone);
        sb.append(", modelid=").append(modelid);
        sb.append(", price=").append(price);
        sb.append(", buttime=").append(buttime);
        sb.append(", payWay=").append(payWay);
        sb.append("]");
        return sb.toString();
    }
}