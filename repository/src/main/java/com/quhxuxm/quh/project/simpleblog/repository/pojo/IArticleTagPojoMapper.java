package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleTag;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleTagPojoMapper {
    void create(ArticleTag articleTag);

    void delete(ArticleTag articleTag);
}
