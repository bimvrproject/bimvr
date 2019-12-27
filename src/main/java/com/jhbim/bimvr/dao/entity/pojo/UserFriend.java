package com.jhbim.bimvr.dao.entity.pojo;

import java.io.Serializable;

public class UserFriend implements Serializable {
    private String id;

    private String userphone;

    private String friendphone;

    private Integer islike;

    private Integer type;

    private String message;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getFriendphone() {
        return friendphone;
    }

    public void setFriendphone(String friendphone) {
        this.friendphone = friendphone == null ? null : friendphone.trim();
    }

    public Integer getIslike() {
        return islike;
    }

    public void setIslike(Integer islike) {
        this.islike = islike;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userphone=").append(userphone);
        sb.append(", friendphone=").append(friendphone);
        sb.append(", islike=").append(islike);
        sb.append(", type=").append(type);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}