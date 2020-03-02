package com.jhbim.bimvr.utils;

/**
 * @Description: 返回值状态码
 * @Author: chen
 * @Date: 2019/8/3
 */
public enum ResultStatusEnum {

    /**操作成功*/
    SUCCESS(0,"成功"),
    /**参数错误*/
    PARAM_INVALID(4001,"参数错误"),
    /**操作失败*/
    ERROR(5001,"操作失败"),
    /**结果为空*/
    RESULT_EMPTY(5002,"返回结果为空")
    ;

    private Integer status;
    private String msg;

    ResultStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg(){
        return msg;
    }

    public static String getMsgByStatus(Integer status){
        if(status == null){
            return "";
        }
        for(ResultStatusEnum resultStatusEnum : values()){
            if(resultStatusEnum.getStatus().equals(status)){
                return resultStatusEnum.getMsg();
            }
        }
        return "";
    }

}
