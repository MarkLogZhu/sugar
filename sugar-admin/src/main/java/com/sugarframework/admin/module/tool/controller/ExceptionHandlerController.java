package com.sugarframework.admin.module.tool.controller;

import com.sugarframework.core.base.controller.BaseController;
import com.sugarframework.core.enums.ResultCode;
import com.sugarframework.core.exception.BusinessException;
import com.sugarframework.core.model.ResponseDto;
import com.sugarframework.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @description: 异常处理
 * @date 2019-06-14 13:59
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController extends BaseController {

    public static final String ERROR_VIEW = "error";
    public static final String ERROR_NOHANDLER_FOUND_VIEW = "404";


    /**
     * 统一处理参数校验异常
     * 对象参数接收请求体校验不通过会抛出 MethodArgumentNotValidException
     * 普通参数校验校验不通过会抛出 ConstraintViolationException
     * 必填参数没传校验不通过会抛出 ServletRequestBindingException
     * 请求参数绑定到对象上校验不通过会抛出 BindException
     * </pre>
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    public ModelAndView handleValidationException(HttpServletRequest request, HttpServletResponse response,
                                                  Exception exception) {
        String errorMsg = "";
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            errorMsg = getBindingResultMsg(e.getBindingResult());
        } else if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException e = (ConstraintViolationException) exception;
            errorMsg = e.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) exception;
            errorMsg = e.getParameterName() + " 不能为空";
        } else if (exception instanceof MissingPathVariableException) {
            MissingPathVariableException e = (MissingPathVariableException) exception;
            errorMsg = e.getVariableName() + " 不能为空";
        } else if (exception instanceof BindException) {
            BindException e = (BindException) exception;
            errorMsg = getBindingResultMsg(e.getBindingResult());
        } else {
            return handlerException(request, response, exception);
        }

        return handlerReturnResult(request, response,ResultCode.ERROR_PARAMETER_VALIDATION.getCode() ,errorMsg);
    }

    /**
     * 处理业务异常
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseDto handlerBusinessException(HttpServletRequest request, BusinessException exception) {
        log.error("BusinessException --> url:" + request.getRequestURL().toString(), exception.getMessage());
        return ResponseDto.failure(exception.getMessage());
    }


    /**
     * 处理404异常
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        log.error("Exception --> url:" + request.getRequestURL().toString(), exception.getMessage());
        if (isAccessPage(request)) {
            ModelAndView view = new ModelAndView();
            view.setViewName(ERROR_NOHANDLER_FOUND_VIEW);
            return view;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JsonUtils.toJsonString(ResponseDto.failure(ResultCode.ERROR_NO_HANDLER_FOUND)));
            return null;
        }
    }


    /**
     * 处理运行时未知异常
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView handlerRuntimeException(HttpServletRequest request, HttpServletResponse response, RuntimeException exception) {
        log.error("Exception --> url:" + request.getRequestURL().toString(), exception);
        return handlerReturnResult(request, response, ResultCode.ERROR_UNKNOWN.getCode(), exception.toString());
    }

    /**
     * 处理未知异常
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("Exception --> url:" + request.getRequestURL().toString(), exception.getMessage());
        return handlerReturnResult(request, response, ResultCode.ERROR_UNKNOWN.getCode(), exception.getMessage());
    }


    /**
     * 处理返回结果
     *
     * @param request
     * @param response
     * @param errorCode
     * @param errorMsg
     * @return
     */
    private ModelAndView handlerReturnResult(HttpServletRequest request, HttpServletResponse response, int errorCode, String errorMsg) {
        if (isAccessPage(request)) {
            ModelAndView view = new ModelAndView();
            view.addObject("url", request.getRequestURL());
            view.addObject("exception", errorMsg);
            view.setViewName(ERROR_VIEW);
            return view;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try {
                response.getWriter().write(JsonUtils.toJsonString(ResponseDto.failure(errorCode, errorMsg)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 获取绑定结果错误信息
     *
     * @param result
     * @return
     */
    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }
}
