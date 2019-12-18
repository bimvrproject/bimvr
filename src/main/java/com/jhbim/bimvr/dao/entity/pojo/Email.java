package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer hairuserId;

    private Integer closeduserId;

    private String mail;

    private Date sendtime;

    private Date receivetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", hairuserId=").append(hairuserId);
        sb.append(", closeduserId=").append(closeduserId);
        sb.append(", mail=").append(mail);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", receivetime=").append(receivetime);
        sb.append("]");
        return sb.toString();
    }
}