package com.wentong.ladder.exceptions;

public class ClassInitException extends RuntimeException {

    public ClassInitException(String message) {
        super(message);
    }

    public ClassInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassInitException(Throwable cause) {
        super(cause);
    }

    public ClassInitException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
