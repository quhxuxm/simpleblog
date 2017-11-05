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
    private ArticleAdditionalInfo additionalInfo;
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

    public ArticleAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ArticleAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
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
}
