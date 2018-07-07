package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleTag;

@Repository
public interface IArticleTagPojoMapper {
    void create(ArticleTag articleTag);

    void delete(ArticleTag articleTag);
}
