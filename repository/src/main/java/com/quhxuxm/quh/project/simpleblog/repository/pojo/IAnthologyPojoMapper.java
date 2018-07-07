package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;

@Repository
public interface IAnthologyPojoMapper {
    Anthology findOneById(@Param("id") long id);

    void update(Anthology anthology);

    void create(Anthology anthology);
}
