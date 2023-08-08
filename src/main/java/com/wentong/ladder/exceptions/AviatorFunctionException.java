package com.wentong.ladder.exceptions;

public class AviatorFunctionException extends RuntimeException {

        public AviatorFunctionException(String message) {
            super(message);
        }

        public AviatorFunctionException(String message, Throwable cause) {
            super(message, cause);
        }

        public AviatorFunctionException(Throwable cause) {
            super(cause);
        }

        public AviatorFunctionException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
}
