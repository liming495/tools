package com.githup.liming495.exception;

/**
 * 服务层异常
 *
 * @author Guppy
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable e) {
        this(e.getClass().getSimpleName(), e);
    }
}
