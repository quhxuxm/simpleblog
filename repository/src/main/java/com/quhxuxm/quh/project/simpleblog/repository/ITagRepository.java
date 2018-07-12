package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag findByText(String text);

    Set<Tag> findAllByTextIn(Set<String> tagTexts);

    boolean existsByText(String tagText);
}
