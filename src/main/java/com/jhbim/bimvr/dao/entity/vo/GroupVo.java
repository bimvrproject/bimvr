package com.jhbim.bimvr.dao.entity.vo;


public class GroupVo {
    private String groupno;

    private String groupname;

    private Integer usergroupId;

    private String picture;

    private Integer count=0;

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Integer usergroupId) {
        this.usergroupId = usergroupId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
