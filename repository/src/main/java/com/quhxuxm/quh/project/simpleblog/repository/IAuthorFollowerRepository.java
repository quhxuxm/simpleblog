package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.AuthorFollower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorFollowerRepository
        extends JpaRepository<AuthorFollower, AuthorFollower.PK> {
}
