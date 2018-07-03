package com.quhxuxm.quh.project.simpleblog.domain.pojo;

public class AnthologyTag {
    private Long anthologyId;
    private Long tagId;
    private Boolean selected;
    private Double weight;

    public AnthologyTag() {
        this.selected = true;
        this.weight = 0d;
    }

    public Long getAnthologyId() {
        return anthologyId;
    }

    public void setAnthologyId(Long anthologyId) {
        this.anthologyId = anthologyId;
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
