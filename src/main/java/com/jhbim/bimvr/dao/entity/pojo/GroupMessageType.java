package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class GroupMessageType implements Serializable {
    private String id;

    private String groupno;

    private String msgId;

    private String toUser;

    private String fromTime;

    private String toTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno == null ? null : groupno.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupno=").append(groupno);
        sb.append(", msgId=").append(msgId);
        sb.append(", toUser=").append(toUser);
        sb.append(", fromTime=").append(fromTime);
        sb.append(", toTime=").append(toTime);
        sb.append("]");
        return sb.toString();
    }
}