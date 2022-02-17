package com.githup.liming495.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;

/**
 * 对象工具类
 * @since  2019/5/27 12:55
 * @author by guppy
 */
public abstract class ObjectUtils {
    public static Log log = LogFactory.getLog(ObjectUtils.class);

    /**
     * 检查一个集合是否为空
     *
     * @param c 集合
     * @return 返回值
     */
    public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection c) {
        return c == null || c.size() == 0;
    }

    /**
     * 检查一个字符串是否为空
     *
     * @param s 字符串
     * @return 结果
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    /**
     * 检查一个字符串是否为空
     *
     * @param s 一个字符串
     * @return 是否为空
     */
    public static boolean isNull(String s) {
        return s == null;
    }

    /**
     * 检查一个对象是否为空
     *
     * @param o 一个对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return isEmpty(o.toString());
        } else if (o instanceof Collection) {
            return isEmpty((Collection<?>) o);
        }
        return false;
    }

    /**
     * 检查一个对象数组是否为空
     *
     * @param os 一个对象数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] os) {
        return os == null || os.length == 0;
    }

    /**
     * 检查多个对象当中是否有存在空参数
     *
     * @param os 多个对象
     * @return 是否有存在空参数
     */
    public static boolean hasEmpty(Object... os) {
        if (isEmpty(os)) {
            return true;
        }
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查多个对象当中是否全部是空
     *
     * @param os 多个对象
     * @return 是否全部是空
     */
    public static boolean allEmpty(Object... os) {
        if (isEmpty(os)) {
            return true;
        }
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查多个对象当中是否全部是空
     *
     * @param os 多个对象
     * @return 是否全部是空
     */
    public static boolean allNull(Object... os) {
        if (isEmpty(os)) {
            return true;
        }
        for (Object o : os) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查多个字符串当中是否有存在空参数
     *
     * @param ss 多个字符串
     * @return 是否有存在空参数
     */
    public static boolean hasEmpty(String... ss) {
        if (isEmpty(ss)) {
            return true;
        }
        for (String s : ss) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }
}
