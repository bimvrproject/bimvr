package com.jhbim.bimvr.dao.entity.vo;

/**
 * 回复评论表Vo类
 */
public class ReplyVo {
    private String tousername;  //回复评论人的名称

    private String content;     //回复评论的内容

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
