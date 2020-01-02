package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class GroupCluster implements Serializable {
    private Integer id;

    private String groupno;

    private String groupname;

    private Integer roleId;

    private Integer usergroupId;

    private String userId;

    private Integer level;

    private Date createtime;

    private Integer type;

    private String picture;

    private String brief;

    private String remark;

    private Integer isrecommend;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno == null ? null : groupno.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Integer usergroupId) {
        this.usergroupId = usergroupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsrecommend() {
        return isrecommend;
    }

    public void setIsrecommend(Integer isrecommend) {
        this.isrecommend = isrecommend;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupno=").append(groupno);
        sb.append(", groupname=").append(groupname);
        sb.append(", roleId=").append(roleId);
        sb.append(", usergroupId=").append(usergroupId);
        sb.append(", userId=").append(userId);
        sb.append(", level=").append(level);
        sb.append(", createtime=").append(createtime);
        sb.append(", type=").append(type);
        sb.append(", picture=").append(picture);
        sb.append(", brief=").append(brief);
        sb.append(", remark=").append(remark);
        sb.append(", isrecommend=").append(isrecommend);
        sb.append("]");
        return sb.toString();
    }
}