package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyRepository extends JpaRepository<Anthology, Long> {
    long countByAuthor(Author author);
}
