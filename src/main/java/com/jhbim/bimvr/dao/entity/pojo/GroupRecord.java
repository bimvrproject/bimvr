package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class GroupRecord implements Serializable {
    private String id;

    private String groupid;

    private Integer roleId;

    private String userphone;

    private String invitephone;

    private Integer level;

    private Integer islike;

    private String message;

    private String grtime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getInvitephone() {
        return invitephone;
    }

    public void setInvitephone(String invitephone) {
        this.invitephone = invitephone == null ? null : invitephone.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIslike() {
        return islike;
    }

    public void setIslike(Integer islike) {
        this.islike = islike;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getGrtime() {
        return grtime;
    }

    public void setGrtime(String grtime) {
        this.grtime = grtime == null ? null : grtime.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupid=").append(groupid);
        sb.append(", roleId=").append(roleId);
        sb.append(", userphone=").append(userphone);
        sb.append(", invitephone=").append(invitephone);
        sb.append(", level=").append(level);
        sb.append(", islike=").append(islike);
        sb.append(", message=").append(message);
        sb.append(", grtime=").append(grtime);
        sb.append("]");
        return sb.toString();
    }
}