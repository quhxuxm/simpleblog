package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagPojoMapper {
    void create(Tag tag);

    Tag findOneByText(@Param("text") String text);

    Tag findOneById(@Param("id") long id);
}
