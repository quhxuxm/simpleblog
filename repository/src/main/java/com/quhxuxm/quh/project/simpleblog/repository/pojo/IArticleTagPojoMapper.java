package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleTag;

@Repository
@Mapper
public interface IArticleTagPojoMapper {
    void create(ArticleTag articleTag);

    void delete(ArticleTag articleTag);
}
