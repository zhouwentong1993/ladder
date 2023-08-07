package com.wentong.ladder.exceptions;

public class ClassInitializeException extends RuntimeException {

    public ClassInitializeException(String message) {
        super(message);
    }

    public ClassInitializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassInitializeException(Throwable cause) {
        super(cause);
    }

    public ClassInitializeException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
