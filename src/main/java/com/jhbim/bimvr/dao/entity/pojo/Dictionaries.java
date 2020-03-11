package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;
import java.util.Date;

public class Dictionaries implements Serializable {
    private Integer id;

    private String dicName;

    private Integer parentid;

    private String materialId;

    private String url;

    private String thumbnail;

    private String icon;

    private Date createtome;

    private String userphone;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId == null ? null : materialId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Date getCreatetome() {
        return createtome;
    }

    public void setCreatetome(Date createtome) {
        this.createtome = createtome;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dicName=").append(dicName);
        sb.append(", parentid=").append(parentid);
        sb.append(", materialId=").append(materialId);
        sb.append(", url=").append(url);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", icon=").append(icon);
        sb.append(", createtome=").append(createtome);
        sb.append(", userphone=").append(userphone);
        sb.append(", state=").append(state);
        sb.append("]");
        return sb.toString();
    }
}