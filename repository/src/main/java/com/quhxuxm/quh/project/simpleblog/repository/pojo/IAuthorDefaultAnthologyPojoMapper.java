package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorDefaultAnthology;
import org.apache.ibatis.annotations.Param;

public interface IAuthorDefaultAnthologyPojoMapper {
    void create(AuthorDefaultAnthology authorDefaultAnthology);

    void update(AuthorDefaultAnthology authorDefaultAnthology);

    AuthorDefaultAnthology findOneByAuthorId(
            @Param("authorId")
                    long authorId);
}
