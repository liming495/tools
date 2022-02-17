package com.githup.liming495.utils;

/**
 * 是否相等的对比器
 *
 * @author Guppy
 */
public interface Equation<T> {
    /**
     * 对比是否相等
     *
     * @param t1    参照物
     * @param t2    对比对象
     * @return  结果
     */
    boolean equals(T t1, T t2);
}