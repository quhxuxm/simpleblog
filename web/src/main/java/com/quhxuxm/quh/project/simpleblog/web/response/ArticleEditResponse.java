package com.quhxuxm.quh.project.simpleblog.web.response;

import java.util.ArrayList;
import java.util.List;

public class ArticleEditResponse {
    public static enum ErrorCode {
        SYSTEM_ERROR,
        TITLE_IS_EMPTY,
        TITLE_TOO_LONG,
        TITLE_TOO_SHORT,
        CONTENT_IS_EMPTY,
        CONTENT_TOO_LONG,
        CONTENT_TOO_SHORT
    }

    private Long articleId;
    private boolean success;
    private List<ErrorCode> errorCodes;

    public ArticleEditResponse() {
        this.success = true;
        this.errorCodes = new ArrayList<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public List<ErrorCode> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<ErrorCode> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
