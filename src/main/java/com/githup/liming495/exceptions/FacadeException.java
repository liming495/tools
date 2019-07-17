package com.githup.liming495.exception;

/**
 * 表层异常
 *
 * @author Guppy
 */
public class FacadeException extends RuntimeException {
    public FacadeException(String msg, Throwable e) {
        super(msg, e);
    }

    public FacadeException(String msg) {
        super(msg);
    }

    public FacadeException(Throwable e) {
        this(e.getClass().getSimpleName(), e);
    }
}
