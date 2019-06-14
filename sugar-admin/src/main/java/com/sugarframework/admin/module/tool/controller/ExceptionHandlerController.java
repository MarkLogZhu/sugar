package com.sugarframework.admin.module.tool.controller;

import com.sugarframework.core.base.controller.BaseController;
import com.sugarframework.core.enums.ResultCode;
import com.sugarframework.core.exception.BusinessException;
import com.sugarframework.core.model.ResponseDto;
import com.sugarframework.core.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * 处理异常
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        log.error("Exception --> url:" + request.getRequestURL().toString(), exception.getMessage());
        if (isAccessPage(request)) {
            ModelAndView view = new ModelAndView();
            view.addObject("url", request.getRequestURL());
            view.addObject("exception", exception.getMessage());
            view.setViewName(ERROR_VIEW);
            return view;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JsonUtils.toJsonString(ResponseDto.failure(ResultCode.ERROR_UNKNOWN.getCode(), exception.getMessage())));
            return null;
        }

    }


}
