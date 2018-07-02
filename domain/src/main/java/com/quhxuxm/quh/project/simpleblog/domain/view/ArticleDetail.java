package com.quhxuxm.quh.project.simpleblog.domain.view;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Article;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleAdditionalInfo;

import java.io.Serializable;

public class ArticleDetail implements Serializable {
    private Article article;
    private ArticleAdditionalInfo additionalInfo;
    private Long authorId;
    private String authorNickName;
    private Long authorIconImageId;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public ArticleAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ArticleAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public Long getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(Long authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }
}
