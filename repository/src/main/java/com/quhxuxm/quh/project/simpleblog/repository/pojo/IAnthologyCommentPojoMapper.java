package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnthologyCommentPojoMapper {
    void update(AnthologyComment comment);

    void create(AnthologyComment comment);

    AnthologyComment findOneById(
            @Param("id")
                    Long id);
}
