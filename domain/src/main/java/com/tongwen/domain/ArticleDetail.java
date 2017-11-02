package com.tongwen.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleDetail implements Serializable {
    private Long id;
    private String title;
    private Date publishDate;
    private Date createDate;
    private Long authorId;
    private String authorNickName;
    private Long authorIconImageId;
    private String content;
    private Long viewNumber;
    private Long commentNumber;
    private Long praiseNumber;
    private Long bookmarkNumber;
    private Long authorArticleNumber;
    private Long authorCommentNumber;
    private Long authorAnthologyNumber;
    private Long anthologyId;
    private String anthologyTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public Long getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(Long authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getAuthorArticleNumber() {
        return authorArticleNumber;
    }

    public void setAuthorArticleNumber(Long authorArticleNumber) {
        this.authorArticleNumber = authorArticleNumber;
    }

    public Long getAuthorCommentNumber() {
        return authorCommentNumber;
    }

    public void setAuthorCommentNumber(Long authorCommentNumber) {
        this.authorCommentNumber = authorCommentNumber;
    }

    public Long getAuthorAnthologyNumber() {
        return authorAnthologyNumber;
    }

    public void setAuthorAnthologyNumber(Long authorAnthologyNumber) {
        this.authorAnthologyNumber = authorAnthologyNumber;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public String getAnthologyTitle() {
        return anthologyTitle;
    }

    public void setAnthologyTitle(String anthologyTitle) {
        this.anthologyTitle = anthologyTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
