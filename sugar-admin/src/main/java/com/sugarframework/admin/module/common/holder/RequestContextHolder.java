package com.sugarframework.admin.module.common.holder;

import com.sugarframework.admin.module.common.model.UserDto;

import javax.servlet.http.HttpServletRequest;

/**
  * @description: 请求上下文容器
  * @author zhu
  * @date 2019-06-24 14:59
  */
public class RequestContextHolder {

    private static final ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<UserDto> USER_THREAD_LOCAL = new ThreadLocal<>();


    public static void add(HttpServletRequest request){
        REQUEST_THREAD_LOCAL.set(request);
    }

    public static void add(UserDto userDto){
        USER_THREAD_LOCAL.set(userDto);
    }

    public static void remove(){
        REQUEST_THREAD_LOCAL.remove();
        USER_THREAD_LOCAL.remove();
    }

}
