package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyTag;

@Repository
public interface IAnthologyTagPojoMapper {
    void create(AnthologyTag anthologyTag);

    void delete(AnthologyTag anthologyTag);
}
