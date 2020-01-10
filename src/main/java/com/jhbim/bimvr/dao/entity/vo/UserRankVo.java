package com.jhbim.bimvr.dao.entity.vo;

/**
 * @author shuihu
 * @create 2020-01-10 13:26
 */
public class UserRankVo {

    private int sequence;

    private String pricture;

    private String userName;

    private String image;

    private String posotion;

    private Integer account;

    private Integer modelNum;


    public UserRankVo(){}

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getPricture() {
        return pricture;
    }

    public void setPricture(String pricture) {
        this.pricture = pricture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosotion() {
        return posotion;
    }

    public void setPosotion(String posotion) {
        this.posotion = posotion;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getModelNum() {
        return modelNum;
    }

    public void setModelNum(Integer modelNum) {
        this.modelNum = modelNum;
    }
}
