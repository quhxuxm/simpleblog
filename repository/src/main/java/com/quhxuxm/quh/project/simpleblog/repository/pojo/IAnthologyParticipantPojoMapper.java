package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyParticipant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAnthologyParticipantPojoMapper {
    List<AnthologyParticipant> findAllByAnthologyIdOrderByParticipateDate(
            @Param("anthologyId")
                    long anthologyId);

    void create(AnthologyParticipant participant);

    void update(AnthologyParticipant participant);
}
