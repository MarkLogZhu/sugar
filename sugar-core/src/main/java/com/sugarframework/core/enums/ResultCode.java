package com.sugarframework.core.enums;

/**
 * @author zhu
 * @description: 请求响应结果枚举类
 * @date 2019-06-14 13:45
 */
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAIL(43003, "操作失败"),
    ERROR_PARAMETER_VALIDATION(50000, "参数检验失败"),
    ERROR_NOT_LOGIN(50001, "用户未登录"),
    ACCOUNT_NOT_EXIST(50002, "账号不存在"),
    PASSWORD_WRONG(50003, "账号或密码错误"),
    ACCOUNT_STATUS_FREEZE(50004, "账号当前被冻结,请联系系统管理员处理"),
    ERROR_NO_HANDLER_FOUND(404, "接口不存在"),
    ERROR_UNKNOWN(60000, "未知的内部错误"),
    ERROR_BUSINESS(60001, "通用业务错误");

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
