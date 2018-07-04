package com.quhxuxm.quh.project.simpleblog.domain.pojo;

import java.io.Serializable;

public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 5077971124151612394L;
    private Long articleId;
    private Long tagId;
    private Boolean selected;
    private Double weight;

    public ArticleTag() {
        this.selected = true;
        this.weight = 0d;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
