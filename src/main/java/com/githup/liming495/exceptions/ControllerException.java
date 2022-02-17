package com.githup.liming495.exception;

/**
 *  控制层异常
 *
 * @author Guppy
 */
public class ControllerException extends RuntimeException {
    public ControllerException(String msg, Throwable e) {
        super(msg, e);
    }

    public ControllerException(String msg) {
        super(msg);
    }

    public ControllerException(Throwable e) {
        this(e.getClass().getSimpleName(), e);
    }
}
