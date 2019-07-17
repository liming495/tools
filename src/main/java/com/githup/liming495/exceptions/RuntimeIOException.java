package com.githup.liming495.exception;

/**
 * 运行时IO异常
 *
 * @author Guppy
 */
public class RuntimeIOException extends RuntimeException {
    public RuntimeIOException(String msg, Throwable e) {
        super(msg, e);
    }
}
