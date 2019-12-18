package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String roleName;

    private Long rolePrice;

    private String image;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Long getRolePrice() {
        return rolePrice;
    }

    public void setRolePrice(Long rolePrice) {
        this.rolePrice = rolePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", rolePrice=").append(rolePrice);
        sb.append(", image=").append(image);
        sb.append("]");
        return sb.toString();
    }
}