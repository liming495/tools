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
     * @param t1
     * @param t2
     * @return
     */
    boolean canMerge(T t1, T t2);
}
