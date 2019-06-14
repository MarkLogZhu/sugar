package com.sugarframework.core.exception;

import com.sugarframework.core.base.exception.BaseException;
import com.sugarframework.core.enums.ResultCode;

/**
  * @description: 业务异常
  * @author zhu
  * @date 2019-06-14 13:45
  */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(ResultCode.ERROR_BUSINESS.getCode(), message);
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }

}
