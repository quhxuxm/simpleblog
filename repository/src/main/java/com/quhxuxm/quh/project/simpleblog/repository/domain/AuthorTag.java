package com.quhxuxm.quh.project.simpleblog.repository.domain;

import java.io.Serializable;

public class AuthorTag implements Serializable {
    private static final long serialVersionUID = -4652674514830940378L;
    private Long authorId;
    private Long tagId;
    private Boolean selected;
    private Double weight;

    public AuthorTag() {
        this.selected = true;
        this.weight = 0d;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
