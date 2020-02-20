package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class ProjectGroup implements Serializable {
    private Long id;

    private String projectid;

    private String groupno;

    private Integer grouptype;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno == null ? null : groupno.trim();
    }

    public Integer getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(Integer grouptype) {
        this.grouptype = grouptype;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectid=").append(projectid);
        sb.append(", groupno=").append(groupno);
        sb.append(", grouptype=").append(grouptype);
        sb.append("]");
        return sb.toString();
    }
}