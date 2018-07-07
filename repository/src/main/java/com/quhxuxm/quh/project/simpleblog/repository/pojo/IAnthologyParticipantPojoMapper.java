package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyParticipant;

public interface IAnthologyParticipantPojoMapper {
    List<AnthologyParticipant> findAllByAnthologyIdOrderByParticipateDate(@Param("anthologyId") long anthologyId);

    void create(AnthologyParticipant participant);

    void delete(AnthologyParticipant participant);
}
