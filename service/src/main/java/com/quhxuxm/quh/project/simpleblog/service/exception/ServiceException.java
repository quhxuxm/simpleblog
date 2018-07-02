package com.quhxuxm.quh.project.simpleblog.service.exception;

public class ServiceException extends Exception {
    public static enum Code {
        SYSTEM_ERROR,
        AUTHOR_NOT_EXIST,
        AUTHOR_NOT_ASSIGNED,
        ANTHOLOGY_NOT_EXIST,
        ANTHOLOGY_NOT_BELONG_TO_AUTHOR,
        ANTHOLOGY_NOT_ASSIGNED,
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

    public ServiceException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace, Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
