package com.quhxuxm.quh.project.simpleblog.service.dto;

public class ArticlePraiseDTO {
    private Long authorId;
    private Long articleId;

    public Long getAuthorId() {
        return authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
