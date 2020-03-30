package com.githup.liming495.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 请求对象工具类，用于在其它层获得一些请求对象中的属性
 * @since  2019/5/27 12:55
 * @author by guppy
 */
public abstract class RequestUtils {
    private static ThreadLocal<HttpServletRequest> local = new ThreadLocal<HttpServletRequest>();

    /**
     * 设置当前请求对象给本地线程
     * @param req result
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
     * @param key 关键字
     * @return 请求参数
     */
    public static String getParameter(String key) {
        return getRequest().getParameter(key);
    }

    /**
     * 获取请求头参数
     *
     * @param key 关键字
     * @return 请求头参数
     */
    public static String getHead(String key) {
        return getRequest().getHeader(key);
    }

    /**
     * 获取本地对象
     * @return 本地对象
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

    /**
     * 将url参数转换成map
     *
     * @param param URL请求参数
     * @return mpa
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (ObjectUtils.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (String para : params) {
            String[] p = para.split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            sb.append("&");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
