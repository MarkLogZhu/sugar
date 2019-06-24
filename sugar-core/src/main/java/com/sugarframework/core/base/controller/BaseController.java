package com.sugarframework.core.base.controller;

import com.sugarframework.core.util.web.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhu
 * @description: Controller 基类
 * @date 2019-06-14 13:00
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 是否是页面访问,判断条件为是否是 Ajax 请求
     *
     * @param request
     * @return
     */
    public boolean isAccessPage(HttpServletRequest request) {
        return !ServletUtils.isAjaxRequest(request);
    }

}
