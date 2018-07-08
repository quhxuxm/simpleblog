package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorDefaultAnthology;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorDefaultAnthologyPojoMapper {
    void create(AuthorDefaultAnthology authorDefaultAnthology);

    void update(AuthorDefaultAnthology authorDefaultAnthology);
}
