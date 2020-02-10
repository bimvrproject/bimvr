package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private String id;

    private String projectName;

    private String projectModelAddr;

    private Integer projectStatus;

    private String projectAddress;

    private String projectContent;

    private Date createTime;

    private Date endTime;

    private Integer completion;

    private String rvtaddress;

    private String priceaddress;

    private String drawingaddress;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectModelAddr() {
        return projectModelAddr;
    }

    public void setProjectModelAddr(String projectModelAddr) {
        this.projectModelAddr = projectModelAddr == null ? null : projectModelAddr.trim();
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress == null ? null : projectAddress.trim();
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent == null ? null : projectContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public String getRvtaddress() {
        return rvtaddress;
    }

    public void setRvtaddress(String rvtaddress) {
        this.rvtaddress = rvtaddress == null ? null : rvtaddress.trim();
    }

    public String getPriceaddress() {
        return priceaddress;
    }

    public void setPriceaddress(String priceaddress) {
        this.priceaddress = priceaddress == null ? null : priceaddress.trim();
    }

    public String getDrawingaddress() {
        return drawingaddress;
    }

    public void setDrawingaddress(String drawingaddress) {
        this.drawingaddress = drawingaddress == null ? null : drawingaddress.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectName=").append(projectName);
        sb.append(", projectModelAddr=").append(projectModelAddr);
        sb.append(", projectStatus=").append(projectStatus);
        sb.append(", projectAddress=").append(projectAddress);
        sb.append(", projectContent=").append(projectContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", completion=").append(completion);
        sb.append(", rvtaddress=").append(rvtaddress);
        sb.append(", priceaddress=").append(priceaddress);
        sb.append(", drawingaddress=").append(drawingaddress);
        sb.append("]");
        return sb.toString();
    }
}