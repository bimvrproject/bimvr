package com.jhbim.bimvr.system.enums;


/**
 * @param
 * @author wcf
 * @description 错误代码
 * @date 2018/1/5
 * @return
 */
public enum ResultStatusCode {
    OK(0, "OK"),
    FAIL(-1,"操作失败"),
    RegiterSuccess(3, "注册成功"),
    NoRegiter(4, "未注册"),
    UserVo(5, "UserVo查询成功"),
    CREATE_PROJECT_CEILING(10,"您的创建项目次数已上限,如需创建,请与我们联系..."),
    ORDINARY(11,"您目前不是会员，无法操作..."),
    UPPER_MODEL_CEILING(12,"您上架的模型数量已达到最大值..."),
    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    SUCCESS(200, "请求处理成功"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENTID(402, "无效的密钥"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    SYSTEM_ERR(500, "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "账号错误或密码错误"),
    IS_NOT_EXIST(1000, "该账户不存在..."),
    LOGINED_IN(10001, "该用户已登录"),
    NOT_EXIST_BUSINESS(10002, "该商家不存在"),
    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),
    BIND_PHONE(10010, "请绑定手机号"),
    UPLOAD_ERROR(20000, "上传文件异常"),
    INVALID_CAPTCHA(30005, "无效的验证码"),
    USER_FROZEN(40000, "该用户已被冻结");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
