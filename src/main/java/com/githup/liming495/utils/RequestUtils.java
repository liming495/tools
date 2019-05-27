package com.githup.liming495.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 请求对象工具类，用于在其它层获得一些请求对象中的属性
 * @since  2019/5/27 12:55
 * @author by guppy
 */
public abstract class RequestUtils {
    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<HttpServletRequest>();

    /**
     * 设置当前请求对象给本地线程
     *
     * @param req
     */
    public static void save(HttpServletRequest req) {
        local.set(req);
    }

    /**
     * 获取本地线程中的请求对象
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest req = local.get();
        if (req == null) {
            throw new RuntimeException("not found the local request in thread local");
        }
        return req;
    }

    /**
     * 获取请求参数
     *
     * @param key
     * @return
     */
    public static String getParameter(String key) {
        return getRequest().getParameter(key);
    }

    /**
     * 获取请求头参数
     *
     * @param key
     * @return
     */
    public static String getHead(String key) {
        return getRequest().getHeader(key);
    }

    /**
     * 获取本地对象
     * @return
     */
    public static Locale getLocale() {
        return getRequest().getLocale();
    }

    /**
     * 获取用户IP
     *
     * @return String
     */
    public static String getRemoteAddr() {
        HttpServletRequest httpReq = getRequest();
        String ip = httpReq.getHeader("x-forwarded-for");
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpReq.getHeader("Proxy-Client-IP");
        }
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpReq.getHeader("WL-Proxy-Client-IP");
        }
        if (ObjectUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpReq.getRemoteAddr();
        }
        return ip;

    }
}
