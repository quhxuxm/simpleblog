package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IAuthorTagRepository
        extends JpaRepository<AuthorTag, AuthorTag.PK> {
    Set<AuthorTag> findAllByPkAuthorAndIsSelectedIsTrue(Author author);

    Set<AuthorTag> findAllByPkAuthor(Author author);
}
