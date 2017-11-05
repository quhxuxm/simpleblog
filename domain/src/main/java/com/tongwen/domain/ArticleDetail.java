package com.tongwen.domain;

import java.io.Serializable;
import java.util.Date;

public class ArticleDetail implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Date publishDate;
    private Long authorId;
    private String authorNickName;
    private Long authorIconImageId;
    private Long anthologyId;
    private String anthologyTitle;
    private ArticleAdditionalInfo additionalInfo;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public ArticleAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ArticleAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
