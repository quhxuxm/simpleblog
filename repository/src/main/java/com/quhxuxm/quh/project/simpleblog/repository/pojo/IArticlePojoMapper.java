package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticlePojoMapper {
    void create(Article article);

    void update(Article article);

    Article findOneById(@Param("id") long id);
}
