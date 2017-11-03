package com.tongwen.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Long id;
    private String title;
    private Date publishDate;
    private Date updateDate;
    private Date createDate;
    private String content;
    private String summary;
    private Long viewNumber;
    private Long commentNumber;
    private Long praiseNumber;
    private Long bookmarkNumber;
    private Long anthologyId;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title='" + title + '\''
                + ", publishDate=" + publishDate + ", updateDate=" + updateDate
                + ", createDate=" + createDate + ", content='" + content + '\''
                + ", summary='" + summary + '\'' + ", viewNumber=" + viewNumber
                + ", commentNumber=" + commentNumber + ", praiseNumber="
                + praiseNumber + ", bookmarkNumber=" + bookmarkNumber
                + ", anthologyId=" + anthologyId + '}';
    }
}
