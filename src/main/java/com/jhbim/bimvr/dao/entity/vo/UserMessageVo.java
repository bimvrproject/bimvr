package com.jhbim.bimvr.dao.entity.vo;



/**
 * @author shuihu
 * @create 2020-01-06 17:32
 */
public class UserMessageVo {

    private String userName;

    private String phone;

    private String pricture;

    private Integer state;

    private Integer loginFlag;

    private String posotion;

    private Integer unread;

    public UserMessageVo(){}

    public String getPosotion() {
        return posotion;
    }

    public void setPosotion(String posotion) {
        this.posotion = posotion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPricture() {
        return pricture;
    }

    public void setPricture(String pricture) {
        this.pricture = pricture;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }
}
