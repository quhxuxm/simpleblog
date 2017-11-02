package com.tongwen.service.exception;

public class ServiceException extends Exception {
    public static enum Code {
        ILLEGAL_STATUS,
        SYSTEM_ERROR,
        AUTHOR_NOT_EXIST,
        AUTHENTICATION_TOKEN_EXIST,
        AUTHENTICATION_NICK_NAME_EXIST
    }

    private Code code;

    public ServiceException(Code code) {
        this.code = code;
    }

    public ServiceException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, Code code) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
