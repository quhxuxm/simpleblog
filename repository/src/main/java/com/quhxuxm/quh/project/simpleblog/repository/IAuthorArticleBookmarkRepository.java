package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.AuthorArticleBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorArticleBookmarkRepository
        extends JpaRepository<AuthorArticleBookmark, AuthorArticleBookmark.PK> {
}
