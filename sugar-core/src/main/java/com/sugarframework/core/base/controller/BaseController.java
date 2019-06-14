package com.sugarframework.core.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
  * @description: Controller 基类
  * @author zhu
  * @date 2019-06-14 13:00
  */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 是否是页面访问
     * @param request
     * @return
     */
    public boolean isAccessPage(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(header);
        return !isAjax;
    }

}
