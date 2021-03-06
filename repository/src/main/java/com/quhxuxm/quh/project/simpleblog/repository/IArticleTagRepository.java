package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.ArticleTag;
import com.quhxuxm.quh.project.simpleblog.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface IArticleTagRepository
        extends JpaRepository<ArticleTag, ArticleTag.PK> {
    Set<ArticleTag> findAllByPkArticleAndIsSelectedIsTrue(Article article);

    Set<ArticleTag> findAllByPkArticle(Article article);

    Set<ArticleTag> findAllByPkTag(Tag tag);

    Page<ArticleTag> findAllByPkTagIn(Pageable pageable, Collection<Tag> tags);
}
