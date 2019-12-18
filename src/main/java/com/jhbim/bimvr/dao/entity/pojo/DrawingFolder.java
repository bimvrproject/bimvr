package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class DrawingFolder implements Serializable {
    private Integer id;

    private String foldername;

    private Integer drawingId;

    private Integer userId;

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

    public Integer getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(Integer drawingId) {
        this.drawingId = drawingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foldername=").append(foldername);
        sb.append(", drawingId=").append(drawingId);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}