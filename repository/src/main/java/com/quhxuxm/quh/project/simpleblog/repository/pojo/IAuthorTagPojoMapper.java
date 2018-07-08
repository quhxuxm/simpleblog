package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorTag;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorTagPojoMapper {
    void create(AuthorTag authorTag);

    void delete(AuthorTag authorTag);
}
