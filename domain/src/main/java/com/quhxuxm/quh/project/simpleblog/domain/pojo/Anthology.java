package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;
import java.util.Date;

public class Anthology implements Serializable {
    private Long id;
    private String title;
    private String summary;
    private Date createDate;
    private Date updateDate;
    private Date publishDate;
    private Long authorId;
    private Long coverImageId;
    private Long additionalInfoId;
    private Boolean isPublished;

    public Anthology() {
        this.createDate = new Date();
        this.updateDate = this.createDate;
        this.isPublished = false;
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

    public Long getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(Long coverImageId) {
        this.coverImageId = coverImageId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getAdditionalInfoId() {
        return additionalInfoId;
    }

    public void setAdditionalInfoId(Long additionalInfoId) {
        this.additionalInfoId = additionalInfoId;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }
}
