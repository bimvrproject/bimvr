package com.jhbim.bimvr.dao.entity.vo;

import java.util.Date;

//管理界面用户信息VO
public class ManagementUserVo {
    private String phone;  //手机号
    private String rolename; //会员名称
    private Date memberend; //到期时间
    private String feedbackcontene; //反馈内容

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getMemberend() {
        return memberend;
    }

    public void setMemberend(Date memberend) {
        this.memberend = memberend;
    }

    public String getFeedbackcontene() {
        return feedbackcontene;
    }

    public void setFeedbackcontene(String feedbackcontene) {
        this.feedbackcontene = feedbackcontene;
    }
}
