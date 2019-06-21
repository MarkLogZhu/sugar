package com.sugarframework.core.base.controller;

import com.sugarframework.core.util.StringUtils;
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
     * 是否是页面访问
     *
     * @param request
     * @return
     */
    public boolean isAccessPage(HttpServletRequest request) {
        String requestAccept = request.getHeader("accept");
        String contentType = "text/html";
        if (StringUtils.isNotEmpty(requestAccept)) {
            if (StringUtils.contains(requestAccept, "application/json")
                    || StringUtils.contains(requestAccept, "text/javascript")
                    || StringUtils.contains(requestAccept, "text/json")) {
                contentType = "application/json";
            }
        }
        if (contentType.equals("text/html")) {
            return true;
        } else if (contentType.equals("application/json")) {
            return false;
        } else {
            return false;
        }
    }

}
