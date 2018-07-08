package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorDefaultAnthology;

@Repository
@Mapper
public interface IAuthorDefaultAnthologyPojoMapper {
    void create(AuthorDefaultAnthology authorDefaultAnthology);

    void update(AuthorDefaultAnthology authorDefaultAnthology);

}
