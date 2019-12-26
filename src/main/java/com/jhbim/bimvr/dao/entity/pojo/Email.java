package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    private String id;

    private Integer roleId;

    private String hairuserPhone;

    private String closeduserPhone;

    private String mail;

    private Date sendtime;

    private Date receivetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        sb.append(", hairuserPhone=").append(hairuserPhone);
        sb.append(", closeduserPhone=").append(closeduserPhone);
        sb.append(", mail=").append(mail);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", receivetime=").append(receivetime);
        sb.append("]");
        return sb.toString();
    }
}