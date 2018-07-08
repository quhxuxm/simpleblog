package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleComment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IArticleCommentPojoMapper {
    void update(ArticleComment comment);

    void create(ArticleComment comment);

    ArticleComment findOneById(@Param("id") Long id);
}
