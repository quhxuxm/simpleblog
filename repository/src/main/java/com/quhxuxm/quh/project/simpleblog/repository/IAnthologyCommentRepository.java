package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.AnthologyComment;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyCommentRepository
        extends JpaRepository<AnthologyComment, Long> {
    long countByAuthor(Author author);

    long countByAnthology(Anthology anthology);
}
