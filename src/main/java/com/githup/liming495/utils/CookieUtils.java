package com.githup.liming495.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 *
 * @author Guppy
 * @since 2020-04-22 11:36
 */
public class CookieUtils {
    /**
     * 获取cookie
     * @param request   请求
     * @param cookieName    cookie名
     * @return  处理
     */
    public String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 写cookie
     * @param response  响应
     * @param cookieName    cookie名
     * @param value 值
     */
    public void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(5 * 60);
        response.addCookie(cookie);
    }

}
