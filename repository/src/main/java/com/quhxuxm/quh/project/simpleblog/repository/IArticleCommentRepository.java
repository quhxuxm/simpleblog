package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.ArticleComment;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleCommentRepository
        extends JpaRepository<ArticleComment, Long> {
    long countByAuthor(Author author);
}
