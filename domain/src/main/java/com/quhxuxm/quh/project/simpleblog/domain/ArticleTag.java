package com.quhxuxm.quh.project.simpleblog.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 5077971124151612394L;
    @Id
    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "article_id", updatable = false, nullable = false)
    private Long articleId;
    @Id
    @ManyToOne(targetEntity = Article.class)
    @JoinColumn(name = "tag_id", updatable = false, nullable = false)
    private Long tagId;
    @Column(name = "is_selected", nullable = false, updatable = false)
    private Boolean isSelected;
    @Column(name = "weight", nullable = false, scale = 2)
    private Double weight;

    public ArticleTag() {
        this.isSelected = false;
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
