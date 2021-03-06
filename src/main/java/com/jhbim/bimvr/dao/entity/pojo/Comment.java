package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private String id;

    private String composeId;

    private Integer composeType;

    private String content;

    private String fromUserid;

    private Date createTime;

    private Integer accountnum;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getComposeId() {
        return composeId;
    }

    public void setComposeId(String composeId) {
        this.composeId = composeId == null ? null : composeId.trim();
    }

    public Integer getComposeType() {
        return composeType;
    }

    public void setComposeType(Integer composeType) {
        this.composeType = composeType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid == null ? null : fromUserid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAccountnum() {
        return accountnum;
    }

    public void setAccountnum(Integer accountnum) {
        this.accountnum = accountnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", composeId=").append(composeId);
        sb.append(", composeType=").append(composeType);
        sb.append(", content=").append(content);
        sb.append(", fromUserid=").append(fromUserid);
        sb.append(", createTime=").append(createTime);
        sb.append(", accountnum=").append(accountnum);
        sb.append("]");
        return sb.toString();
    }
}