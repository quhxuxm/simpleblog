package com.quhxuxm.quh.project.simpleblog.web.response.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardInfo {
    private Long id;
    private String title;
    private String summary;
    private Date publishDate;
    private Long authorId;
    private String authorNickName;
    private Long authorIconImageId;
    private Long coverImageId;
    private List<CardAdditionalAction> additionalActions;
    private List<CardComment> comments;

    public CardInfo() {
        this.additionalActions = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    public List<CardAdditionalAction> getAdditionalActions() {
        return additionalActions;
    }

    public void setAdditionalActions(
            List<CardAdditionalAction> additionalActions) {
        this.additionalActions = additionalActions;
    }

    public List<CardComment> getComments() {
        return comments;
    }

    public void setComments(List<CardComment> comments) {
        this.comments = comments;
    }
}
