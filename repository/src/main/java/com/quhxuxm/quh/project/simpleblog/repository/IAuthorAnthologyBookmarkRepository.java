package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorAnthologyBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorAnthologyBookmarkRepository extends JpaRepository<AuthorAnthologyBookmark, AuthorAnthologyBookmark.PK> {
    long countByPkAnthology(Anthology anthology);
}
