package com.githup.liming495.utils;

/**
 * 合并器
 *
 * @author Guppy
 */
public interface Merger<T> {
    /**
     * 检查两个对象，是否可以进行合并
     *
     * @param t1 对象一
     * @param t2 对象二
     * @return 结果
     */
    boolean canMerge(T t1, T t2);
}
