package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
    long countByAnthology_Author(Author author);
}
