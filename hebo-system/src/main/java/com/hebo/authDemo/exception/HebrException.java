package com.hebo.authDemo.exception;

/**
 * @ClassName HebrException
 * @Author hebo
 * @Date 2022/6/19 17:29
 **/
public class HebrException extends RuntimeException {


    public HebrException() {
        super();
    }

    public HebrException(String message) {
        super(message);
    }

    public HebrException(String message, Throwable cause) {
        super(message, cause);
    }

    public HebrException(Throwable cause) {
        super(cause);
    }

    protected HebrException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
