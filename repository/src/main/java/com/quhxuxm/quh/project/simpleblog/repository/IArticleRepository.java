package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
}
