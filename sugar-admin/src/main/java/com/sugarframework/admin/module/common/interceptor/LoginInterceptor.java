package com.sugarframework.admin.module.common.interceptor;

import com.sugarframework.admin.config.cache.RedisUtils;
import com.sugarframework.admin.module.common.holder.RequestContextHolder;
import com.sugarframework.admin.module.common.model.UserDto;
import com.sugarframework.core.constant.cache.UserCacheConstant;
import com.sugarframework.core.enums.ResultCode;
import com.sugarframework.core.model.ResponseDto;
import com.sugarframework.core.util.JsonUtils;
import com.sugarframework.core.util.StringUtils;
import com.sugarframework.core.util.web.CookieUtils;
import com.sugarframework.core.util.web.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * @description: 验证是否登录拦截器
  * @author zhu
  * @date 2019-06-24 14:56
  */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN_URL = "/login";

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取登录 token
        String loginToken = CookieUtils.readLoginToken(request);
        if(StringUtils.isEmpty(loginToken)){
            handleReturnResult(request,response);
            return false;
        }
        UserDto userDto = (UserDto) redisUtils.get(loginToken);
        if(userDto == null){
            handleReturnResult(request,response);
            return false;
        }
        RequestContextHolder.add(request);
        RequestContextHolder.add(userDto);
        refreshSessionTime(loginToken);
        return super.preHandle(request, response, handler);
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.remove();
    }

    private void handleReturnResult(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if(ServletUtils.isAjaxRequest(request)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JsonUtils.toJsonString(ResponseDto.failure(ResultCode.ERROR_NOT_LOGIN)));
        }else{
            response.sendRedirect(request.getContextPath()+ LOGIN_URL);
        }
    }

    /**
     * 刷新会话过期时间
     * @param key   key
     */
    private void refreshSessionTime(String key){
        redisUtils.expire(key, UserCacheConstant.LONGIN_SESSION_EXPIRED_IME);
    }
}
