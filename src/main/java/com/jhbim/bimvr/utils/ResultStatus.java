package com.jhbim.bimvr.utils;


import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 统一封装返回值对象
 * @Description: 返回对象包装类
 * @Author: chen
 * @Date: 2019/4/10
 */

public class ResultStatus implements Serializable{

    private static final long serialVersionUID = -3276270216128997806L;

    /**状态码*/
    private Integer status = ResultStatusEnum.SUCCESS.getStatus();

    /**信息*/
    private String msg = "";

    /**数据*/
    private Object data;

    /**分页信息*/
    private PageInfo page;

    public ResultStatus(){
        setStatus(ResultStatusEnum.SUCCESS.getStatus());
    }


    public ResultStatus(Integer status) {
        setStatus(status);
        this.data = null;
        this.page = null;
    }

    public ResultStatus(Integer status, String msg) {
        setStatus(status);
        this.msg = msg;
        this.data = null;
        this.page = null;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    private void setStatus(Integer status){
        this.status = status;
        try{
            if(StringUtils.isNotEmpty(ResultStatusEnum.getMsgByStatus(status))){
                this.msg = ResultStatusEnum.getMsgByStatus(status);
            }
        } catch (Exception e){
        }
    }
}
