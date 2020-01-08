package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class GroupMsg implements Serializable {
    private String msgId;

    private String groupno;

    private String msg;

    private Date toTime;

    private Date fromTime;

    private static final long serialVersionUID = 1L;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getGroupno() {
        return groupno;
    }

    public void setGroupno(String groupno) {
        this.groupno = groupno == null ? null : groupno.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgId=").append(msgId);
        sb.append(", groupno=").append(groupno);
        sb.append(", msg=").append(msg);
        sb.append(", toTime=").append(toTime);
        sb.append(", fromTime=").append(fromTime);
        sb.append("]");
        return sb.toString();
    }
}