package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IAnthologyAdditionalInfoPojoMapper {
    AnthologyAdditionalInfo findOneById(@Param("id") long id);

    void create(AnthologyAdditionalInfo additionalInfo);

    void update(AnthologyAdditionalInfo additionalInfo);
}
