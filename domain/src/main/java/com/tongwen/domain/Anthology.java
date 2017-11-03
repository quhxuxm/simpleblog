package com.tongwen.domain;

import java.io.Serializable;
import java.util.Date;

public class Anthology implements Serializable {
    private Long id;
    private String title;
    private String summary;
    private Date updateDate;
    private Date publishDate;
    private Long authorId;
    private Long followupNumber;
    private Long coverImageId;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public Long getFollowupNumber() {
        return followupNumber;
    }

    public void setFollowupNumber(Long followupNumber) {
        this.followupNumber = followupNumber;
    }

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    @Override
    public String toString() {
        return "Anthology{" + "id=" + id + ", title='" + title + '\''
                + ", summary='" + summary + '\'' + ", updateDate=" + updateDate
                + ", publishDate=" + publishDate + ", authorId=" + authorId
                + ", followupNumber=" + followupNumber + ", coverImageId="
                + coverImageId + '}';
    }
}
