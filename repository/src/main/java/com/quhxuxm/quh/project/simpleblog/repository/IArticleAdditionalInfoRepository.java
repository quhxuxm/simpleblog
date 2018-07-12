package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.ArticleAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleAdditionalInfoRepository extends
        JpaRepository<ArticleAdditionalInfo, Long> {
}
