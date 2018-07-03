package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnthologyAdditionalInfoPojoMapper {
    AnthologyAdditionalInfo findOneByAnthologyId(
            @Param("anthologyId")
                    long anthologyId);

    void create(AnthologyAdditionalInfo additionalInfo);

    void update(AnthologyAdditionalInfo additionalInfo);
}
