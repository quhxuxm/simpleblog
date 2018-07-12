package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.AnthologyParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnthologyParticipantRepository
        extends JpaRepository<AnthologyParticipant, AnthologyParticipant.PK> {
    boolean existsByPkAndIsDeletedIsFalse(AnthologyParticipant.PK pk);
}
