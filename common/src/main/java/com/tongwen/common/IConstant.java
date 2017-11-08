package com.tongwen.common;

public interface IConstant {
    enum Role {
        ROLE_AUTHOR;
    }

    interface ISessionAttributeName {
        String AUTHENTICATED_AUTHOR = "authenticatedAuthor";
    }

    interface IRequestParamValue {
        String BACKEND_ERROR_CODE_DEFAULT_VALUE = "unknown";
    }

    enum LoginStatus {
        ERROR_AUTHOR_NOT_EXIST,
        ERROR_SYSTEM,
        REGISTER_SUCCESS
    }

    interface IUrlFormat {
        String LOGIN_REDIRECT_URL_FORMAT = "/login?status=%s";
    }

    interface MessageCode {
        String SYSTEM_ERROR_MESSAGE_CODE = "jsp.register.errorMessage.systemError";
        String ANTHOLOGY_DEFAULT_TITLE_MESSAGE_CODE = "java.anthology.title.default";
        String ANTHOLOGY_DEFAULT_SUMMARY_MESSAGE_CODE = "java.anthology.summary.default";
    }
}
