package com.tongwen.domain;

import java.io.Serializable;

public class ArticleAdditionalInfo implements Serializable {
    private Long id;
    private Long viewNumber;
    private Long commentNumber;
    private Long praiseNumber;
    private Long bookmarkNumber;

    public ArticleAdditionalInfo() {
        this.viewNumber = 0L;
        this.commentNumber = 0L;
        this.praiseNumber = 0L;
        this.bookmarkNumber = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Long getBookmarkNumber() {
        return bookmarkNumber;
    }

    public void setBookmarkNumber(Long bookmarkNumber) {
        this.bookmarkNumber = bookmarkNumber;
    }
}
