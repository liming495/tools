package com.githup.liming495.utils;

import java.util.Date;

/**
 * 类工具
 *
 * @author Guppy
 */
public abstract class ClassUtils {
    /**
     * 是不是基础类型
     *
     * @param c 类型
     * @return result
     */
    public static boolean isBaseType(Class<?> c) {
        if (c == Boolean.class || c == boolean.class) {
            return true;
        } else if (c == Byte.class || c == byte.class) {
            return true;
        } else if (c == Short.class || c == short.class) {
            return true;
        } else if (c == Integer.class || c == int.class) {
            return true;
        } else if (c == Float.class || c == float.class) {
            return true;
        } else if (c == Long.class || c == long.class) {
            return true;
        } else if (c == Double.class || c == double.class) {
            return true;
        } else if (c == String.class) {
            return true;
        } else if (c == Date.class) {
            return true;
        }
        return false;
    }

    /**
     * 是不是数组类型
     *
     * @param c 类泛型
     * @return  是否数组
     */
    public static boolean isArray(Class<?> c) {
        return c.isArray();
    }
}
