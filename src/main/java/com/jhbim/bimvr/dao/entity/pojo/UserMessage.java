package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
    private String id;

    private String hairuserPhone;

    private String closeduserPhone;

    private String information;

    private Date createtine;

    private Date endtime;

    private Integer type;

    private Integer stauts;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHairuserPhone() {
        return hairuserPhone;
    }

    public void setHairuserPhone(String hairuserPhone) {
        this.hairuserPhone = hairuserPhone == null ? null : hairuserPhone.trim();
    }

    public String getCloseduserPhone() {
        return closeduserPhone;
    }

    public void setCloseduserPhone(String closeduserPhone) {
        this.closeduserPhone = closeduserPhone == null ? null : closeduserPhone.trim();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", hairuserPhone=").append(hairuserPhone);
        sb.append(", closeduserPhone=").append(closeduserPhone);
        sb.append(", information=").append(information);
        sb.append(", createtine=").append(createtine);
        sb.append(", endtime=").append(endtime);
        sb.append(", type=").append(type);
        sb.append(", stauts=").append(stauts);
        sb.append("]");
        return sb.toString();
    }
}