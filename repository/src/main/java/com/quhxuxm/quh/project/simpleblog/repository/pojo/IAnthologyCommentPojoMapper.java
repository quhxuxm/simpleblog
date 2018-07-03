package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyComment;

public interface IAnthologyCommentPojoMapper {
    void update(AnthologyComment comment);

    void create(AnthologyComment comment);

    AnthologyComment findOneById(Long id);
}
