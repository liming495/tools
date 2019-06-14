package com.githup.liming495.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 集合工具类
 *
 * @author Guppy
 */
public abstract class CollectionUtils {

    /**
     * 删除重复的
     * @param items 数据集合
     * @param equation 对比器
     * @param <T> 类型
     */
    public static <T> void removeDuplicate(List<T> items, Equation<T> equation) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = items.size() - 1; j > i; j--) {
                if (equation.equals(items.get(j), items.get(i))) {
                    items.remove(j);
                }
            }
        }
    }

    /**
     * 根据合并器返回的结果进行项的清除
     * @param source 原数据
     * @param merger 合并器
     * @param <T> 类型
     */
    public static <T> void merge(List<T> source, Merger<T> merger) {
        if (source == null || merger == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < source.size(); i++) {
            for (int j = i + 1; j < source.size(); j++) {
                if (merger.canMerge(source.get(i), source.get(j))) {
                    source.remove(j);
                    source.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    /**
     * 去除不要的项
     * @param source 原数据
     * @param removes 要剔除的对象，对比对象是调用 对象的equals方法，务必实现此方法
     * @param <T> 类型
     */
    @SuppressWarnings("unchecked")
    public static <T> void removeItem(List<T> source, T... removes) {
        if (ObjectUtils.isEmpty(source) || ObjectUtils.isEmpty(removes)) {
            throw new IllegalArgumentException();
        }
        for (T t : removes) {
            Iterator<T> iterator = source.iterator();
            while (iterator.hasNext()) {
                T t2 = iterator.next();
                if (t.equals(t2)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * 将list转换为按逗号分割的字符串
     * @param list 原数组
     * @param <T> 类型
     * @return 字符串
     */
    public static <T> String listToString(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 将list转换数值
     *
     * @param list 数组
     * @return 数值
     */
    public static Long[] listToLongs(List<Long> list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        Long[] ids = new Long[list.size()];
        int i = 0;
        for (Long id : list) {
            ids[i++] = id;
        }
        return ids;
    }

    /**
     * 将按逗号分割的字符串转换为list
     * @param string 原字符串
     * @param tClass 转换成目录的类
     * @param <T> 类型
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> List<T> stringToNumberList(String string, Class<T> tClass) {
        if (string == null) {
            return (List<T>) new ArrayList<Number>();
        }
        string = string.trim();
        if(ObjectUtils.isEmpty(string)){
            return (List<T>) new ArrayList<Number>();
        }
        String[] strArr = string.split(",");
        List<Number> list = new ArrayList<Number>();
        for (String s : strArr) {
            if (tClass.equals(Long.class)) {
                if (s.startsWith("#")){
                    continue;
                }
                list.add(Long.parseLong(s));
            } else if (tClass.equals(Integer.class)) {
                list.add(Integer.parseInt(s));
            } else if (tClass.equals(Short.class)) {
                list.add(Short.parseShort(s));
            } else if (tClass.equals(Byte.class)) {
                list.add(Byte.parseByte(s));
            } else if (tClass.equals(Double.class)) {
                list.add(Double.parseDouble(s));
            } else if (tClass.equals(Float.class)) {
                list.add(Float.parseFloat(s));
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return (List<T>) list;
    }

    /**
     * 数组转List
     * @param ts 原数组
     * @param <T> 类型
     * @return 新数组
     */
    public static <T> List<T> arrayToList(T[] ts) {
        if (ts == null) {
            throw new IllegalArgumentException();
        }
        List<T> datas = new ArrayList<T>(ts.length);
        for (T t : ts) {
            datas.add(t);
        }
        return datas;
    }

    /**
     * 数组变字符串
     *
     * @param os 数组
     * @return 字符串
     */
    public static String Array2String(Object[] os) {
        if (os == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < os.length; i++) {
            sb.append(os[i]);
            if (i < os.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 检查数组中是否有包括某个对象
     *
     * @param os
     *            源数组
     * @param o
     *            可能会包含的对象
     * @return result
     */
    public static boolean arrayIsContains(Object[] os, Object o) {
        if (ObjectUtils.allEmpty(os, o)) {
            return true;
        }
        if (ObjectUtils.hasEmpty(os, o)) {
            return false;
        }
        for (Object co : os) {
            if (co.equals(o)) {
                return true;
            }
        }
        return false;
    }
}
