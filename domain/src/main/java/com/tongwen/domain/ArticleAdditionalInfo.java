package com.tongwen.domain;

public class ArticleAdditionalInfo {
    private Long id;
    private Long viewNumber;
    private Long commentNumber;
    private Long praiseNumber;
    private Long bookmarkNumber;

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
