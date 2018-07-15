package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorAnthologyPraise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorAnthologyPraiseRepository
        extends JpaRepository<AuthorAnthologyPraise, AuthorAnthologyPraise.PK> {
    long countByPkAnthology(Anthology anthology);
}
