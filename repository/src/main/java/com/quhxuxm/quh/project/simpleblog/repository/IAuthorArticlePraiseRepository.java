package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorArticlePraise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorArticlePraiseRepository
        extends JpaRepository<AuthorArticlePraise, AuthorArticlePraise.PK> {
    long countByPkArticle(Article article);
}
