package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
    private Integer id;

    private Integer hairuserId;

    private Integer closeduserId;

    private String information;

    private Date createtine;

    private Date endtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHairuserId() {
        return hairuserId;
    }

    public void setHairuserId(Integer hairuserId) {
        this.hairuserId = hairuserId;
    }

    public Integer getCloseduserId() {
        return closeduserId;
    }

    public void setCloseduserId(Integer closeduserId) {
        this.closeduserId = closeduserId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information == null ? null : information.trim();
    }

    public Date getCreatetine() {
        return createtine;
    }

    public void setCreatetine(Date createtine) {
        this.createtine = createtine;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hairuserId=").append(hairuserId);
        sb.append(", closeduserId=").append(closeduserId);
        sb.append(", information=").append(information);
        sb.append(", createtine=").append(createtine);
        sb.append(", endtime=").append(endtime);
        sb.append("]");
        return sb.toString();
    }
}