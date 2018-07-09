package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "anthology_tag")
public class AnthologyTag implements Serializable {
    private static final long serialVersionUID = 8705523091082159065L;
    @Id
    @ManyToOne(targetEntity = Anthology.class)
    @JoinColumn(name = "anthology_id", nullable = false, referencedColumnName = "id", updatable = false)
    private Long anthologyId;
    @Id
    @ManyToOne(targetEntity = Tag.class)
    @JoinColumn(name = "tag_id", nullable = false, referencedColumnName = "id", updatable = false)
    private Long tagId;
    @Column(name = "is_selected", nullable = false, updatable = false)
    private Boolean isSelected;
    @Column(name = "weight", scale = 2, nullable = false)
    private Double weight;

    public AnthologyTag() {
        this.isSelected = true;
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
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        this.isSelected = selected;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
