package com.quhxuxm.quh.project.simpleblog.service.dto;

public class ArticleViewDTO {
    private Long articleId;
    private Long authorId;

    public Long getArticleId() {
        return articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
