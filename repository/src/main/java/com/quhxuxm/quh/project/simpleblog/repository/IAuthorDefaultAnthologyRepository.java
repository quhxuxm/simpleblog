package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorDefaultAnthology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorDefaultAnthologyRepository extends JpaRepository<AuthorDefaultAnthology,
        AuthorDefaultAnthology.PK> {
}
