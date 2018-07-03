package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleComment;

public interface IArticleCommentPojoMapper {
    void update(ArticleComment comment);

    void create(ArticleComment comment);

    ArticleComment findOneById(Long id);
}
