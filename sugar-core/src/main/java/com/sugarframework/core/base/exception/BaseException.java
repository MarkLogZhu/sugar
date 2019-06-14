package com.sugarframework.core.base.exception;

/**
  * @description: 异常基类
  * @author zhu
  * @date 2019-06-14 13:44
  */
public class BaseException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    public BaseException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
