package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleAdditionalInfo;

@Repository
public interface IArticleAdditionalInfoPojoMapper {
    ArticleAdditionalInfo findOneById(@Param("id") long articleId);

    void create(ArticleAdditionalInfo additionalInfo);

    void update(ArticleAdditionalInfo additionalInfo);
}
