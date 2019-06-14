package com.sugarframework.core.model;

import com.sugarframework.core.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
  * @description: 请求响应结果对象
  * @author zhu
  * @date 2019-06-14 13:51
  */
@Data
public class ResponseDto implements Serializable {

    private int code;

    private String message;

    private Object data;


    private ResponseDto(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    private ResponseDto(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    private ResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private ResponseDto(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseDto success() {
        return new ResponseDto(ResultCode.SUCCESS);
    }

    public static ResponseDto success(Object data) {
        return new ResponseDto(ResultCode.SUCCESS, data);
    }

    public static ResponseDto success(String message, Object data) {
        return new ResponseDto(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static ResponseDto failure(ResultCode resultCode){
        return new ResponseDto(resultCode);
    }

    public static ResponseDto failure(String message){
        return new ResponseDto(ResultCode.ERROR_BUSINESS.getCode(),message);
    }

}
