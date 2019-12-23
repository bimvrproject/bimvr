package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class MeterialFolder implements Serializable {
    private Integer id;

    private String foldername;

    private String meterialId;

    private Integer userId;

    private Date mftime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername == null ? null : foldername.trim();
    }

    public String getMeterialId() {
        return meterialId;
    }

    public void setMeterialId(String meterialId) {
        this.meterialId = meterialId == null ? null : meterialId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getMftime() {
        return mftime;
    }

    public void setMftime(Date mftime) {
        this.mftime = mftime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foldername=").append(foldername);
        sb.append(", meterialId=").append(meterialId);
        sb.append(", userId=").append(userId);
        sb.append(", mftime=").append(mftime);
        sb.append("]");
        return sb.toString();
    }
}