package com.tongwen.common;

public interface IConstant {
    enum Role {
        ROLE_AUTHOR;
    }

    interface ISessionAttributeName {
        String AUTHENTICATED_AUTHOR = "authenticatedAuthor";
    }

    enum LoginStatus {
        ERROR_AUTHOR_NOT_EXIST,
        ERROR_SYSTEM,
        REGISTER_SUCCESS
    }

    interface IUrlFormat {
        String LOGIN_REDIRECT_URL_FORMAT = "/login?status=%s";
    }
}
