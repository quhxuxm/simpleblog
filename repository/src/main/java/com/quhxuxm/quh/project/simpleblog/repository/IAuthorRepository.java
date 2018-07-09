package com.quhxuxm.quh.project.simpleblog.repository;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
}
