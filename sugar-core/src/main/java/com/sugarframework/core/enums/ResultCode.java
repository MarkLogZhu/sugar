package com.sugarframework.core.enums;

/**
  * @description: 请求响应结果枚举类
  * @author zhu
  * @date 2019-06-14 13:45
  */
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAIL(43003, "操作失败"),
    ERROR_NOT_LOGIN(50001,"用户未登录"),
    ACCOUNT_NOT_EXIST(50002,"账号不存在"),
    PASSWORD_WRONG(50003,"账号或密码错误"),
    ACCOUNT_STATUS_FREEZE(50004,"账号当前被冻结,请联系系统管理员处理"),
    ERROR_BUSINESS(60000,"通用业务错误");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
