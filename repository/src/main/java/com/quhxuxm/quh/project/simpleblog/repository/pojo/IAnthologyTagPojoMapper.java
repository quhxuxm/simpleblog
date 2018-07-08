package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyTag;

@Repository
@Mapper
public interface IAnthologyTagPojoMapper {
    void create(AnthologyTag anthologyTag);

    void delete(AnthologyTag anthologyTag);
}
