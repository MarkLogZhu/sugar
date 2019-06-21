package com.sugarframework.core.util.web;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * @description: Cookie 工具类
  * @author zhu
  * @date 2019-06-21 8:56
  */
@Slf4j
public class CookieUtils {
    private final static String COOKIE_DOMAIN = ".marklogzhu.com";
    private final static String COOKIE_NAME = "mmall_login_token";
    private final static String DEFAULT_NAME = "JSESSIONID";

    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            String defaultToken = findTokenByName(DEFAULT_NAME, cks);
            return findLoginTokenByName(COOKIE_NAME, defaultToken, cks);
        }
        return null;
    }

    private static String findTokenByName(String cookieName, Cookie[] cks) {
        for (Cookie ck : cks) {
            if (cookieName.equals(ck.getName())) {
                return ck.getValue();
            }
        }
        return null;
    }

    private static String findLoginTokenByName(String cookieName, String defaultToken, Cookie[] cks) {
        String loginToken = null;
        for (Cookie ck : cks) {
            if (cookieName.equals(ck.getName())) {
                loginToken = ck.getValue();
                if (defaultToken.equals(loginToken)) {
                    return loginToken;
                }
            }
        }
        return loginToken;
    }


    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
        // 开发期间不设置，否则导致 cookie 不携带过来
        // ck.setDomain(COOKIE_DOMAIN);
        ck.setPath("/");//代表设置在根目录
        ck.setHttpOnly(true);

        // 单位是秒。
        // 如果这个maxage不设置的话，cookie就不会写入硬盘，而是写在内存。只在当前页面有效。
        ck.setMaxAge(60 * 60 * 24 * 365);//如果是-1，代表永久
        log.debug("write cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
        response.addCookie(ck);
    }


    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (COOKIE_NAME.equals(ck.getName())) {
                    // 开发期间不设置，否则导致 cookie 不携带过来
                    // ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);//设置成0，代表删除此cookie。
                    log.info("del cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
