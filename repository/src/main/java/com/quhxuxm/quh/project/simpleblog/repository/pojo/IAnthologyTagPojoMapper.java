package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyTag;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnthologyTagPojoMapper {
    void create(AnthologyTag anthologyTag);

    void delete(AnthologyTag anthologyTag);
}
