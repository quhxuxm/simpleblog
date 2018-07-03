package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Tag;
import org.apache.ibatis.annotations.Param;

public interface ITagPojoMapper {
    void save(Tag tag);

    void update(Tag tag);

    Tag findOneByText(
            @Param("text")
                    String text);

    Tag findOneById(
            @Param("id")
                    long id);
}
