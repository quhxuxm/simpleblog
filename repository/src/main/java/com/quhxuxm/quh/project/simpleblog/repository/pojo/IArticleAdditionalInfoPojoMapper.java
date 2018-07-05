package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.ArticleAdditionalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticleAdditionalInfoPojoMapper {
    ArticleAdditionalInfo findOneByArticleId(
            @Param("articleId")
                    long articleId);

    void create(ArticleAdditionalInfo additionalInfo);

    void update(ArticleAdditionalInfo additionalInfo);
}

