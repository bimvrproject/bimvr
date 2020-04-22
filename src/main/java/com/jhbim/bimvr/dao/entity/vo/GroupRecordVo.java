package com.jhbim.bimvr.dao.entity.vo;

import com.jhbim.bimvr.dao.entity.pojo.GroupCluster;
import com.jhbim.bimvr.dao.entity.pojo.User;

public class GroupRecordVo {
    private GroupCluster groupCluster;
    private User inviteUser;
    private String roleimg;

    public String getRoleimg() {
        return roleimg;
    }

    public void setRoleimg(String roleimg) {
        this.roleimg = roleimg;
    }

    public GroupCluster getGroupCluster() {
        return groupCluster;
    }

    public void setGroupCluster(GroupCluster groupCluster) {
        this.groupCluster = groupCluster;
    }

    public User getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }
}
