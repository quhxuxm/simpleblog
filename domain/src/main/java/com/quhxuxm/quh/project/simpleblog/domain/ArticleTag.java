package com.quhxuxm.quh.project.simpleblog.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 5077971124151612394L;

    public static class PK implements Serializable {
        private static final long serialVersionUID = 5077971124151612395L;
        @ManyToOne(targetEntity = Article.class)
        @JoinColumn(name = "article_id", updatable = false, nullable = false)
        private Long articleId;
        @ManyToOne(targetEntity = Article.class)
        @JoinColumn(name = "tag_id", updatable = false, nullable = false)
        private Long tagId;
    }

    @EmbeddedId
    private PK pk;
    @Column(name = "is_selected", nullable = false, updatable = false)
    private Boolean isSelected;
    @Column(name = "weight", nullable = false, scale = 2)
    private Double weight;

    public ArticleTag() {
        this.isSelected = false;
        this.weight = 0d;
    }

    public PK getPk() {
        return pk;
    }

    public void setPk(PK pk) {
        this.pk = pk;
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
