package com.quhxuxm.quh.project.simpleblog.web.response;

public class FailPayload {
    public enum Type {
        AUTHENTICATION_REQUIRED,
        LOGIN_ERROR,
        REGISTER_ERROR_BECAUSE_OF_CREATE_AUTHOR_FAIL,
        SERVICE_ERROR_UNKNOWN,
        ERROR_UNKNOWN,
        INPUT_ERROR_WRONG_ARTICLE_LIST_CATEGORY,
        ARTICLE_NOT_EXIST_ERROR
    }

    private String message;
    private Type type;

    public FailPayload(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
