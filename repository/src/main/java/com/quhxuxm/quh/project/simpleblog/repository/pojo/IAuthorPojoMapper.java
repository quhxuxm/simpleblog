package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorPojoMapper {
    Author findOneByAuthenticationId(
            @Param("authenticationId")
                    long authenticationId);

    Author findOneById(
            @Param("id")
                    long authorId);

    void create(Author author);

    void update(Author author);

    void assignDefaultAnthology(
            @Param("authorId")
                    long authorId,
            @Param("anthologyId")
                    long anthologyId);
}
