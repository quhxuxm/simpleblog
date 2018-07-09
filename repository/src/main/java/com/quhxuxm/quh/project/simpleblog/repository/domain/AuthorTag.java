package com.quhxuxm.quh.project.simpleblog.repository.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author_tag")
public class AuthorTag implements Serializable {
    private static final long serialVersionUID = -4652674514830940378L;
    @Id
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Long authorId;
    @Id
    @ManyToOne(targetEntity = Tag.class)
    @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Long tagId;
    @Column(name = "is_selected", nullable = false, updatable = false)
    private Boolean isSelected;
    @Column(name = "weight", nullable = false, scale = 2)
    private Double weight;

    public AuthorTag() {
        this.isSelected = true;
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
