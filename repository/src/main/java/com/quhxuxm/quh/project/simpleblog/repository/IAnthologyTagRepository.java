package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.AnthologyTag;
import com.quhxuxm.quh.project.simpleblog.domain.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IAnthologyTagRepository
        extends JpaRepository<AnthologyTag, AnthologyTag.PK> {
    Set<AnthologyTag> findAllByPkAnthology(Anthology anthology);

    Set<ArticleTag> findAllByPkAnthologyAndIsSelectedIsTrue(Anthology anthology);
}
