package com.githup.liming495.exception;

/**
 * 数据操作层异常
 *
 * @author Guppy
 */
public class DAOException extends RuntimeException {

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Throwable e) {
        super(msg, e);
    }
}
