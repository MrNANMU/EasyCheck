package com.dasong.easycheck.ioc;

public class CheckerException extends Exception{
    public CheckerException() {
    }

    public CheckerException(String message) {
        super(message);
    }

    public CheckerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckerException(Throwable cause) {
        super(cause);
    }

    public CheckerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
