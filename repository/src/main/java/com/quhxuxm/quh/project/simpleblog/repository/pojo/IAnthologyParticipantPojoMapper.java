package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyParticipant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface IAnthologyParticipantPojoMapper {
    List<AnthologyParticipant> findAllByAnthologyIdOrderByParticipateDate(
            @Param("anthologyId")
                    long anthologyId);

    void create(AnthologyParticipant participant);

    void delete(AnthologyParticipant participant);
}
