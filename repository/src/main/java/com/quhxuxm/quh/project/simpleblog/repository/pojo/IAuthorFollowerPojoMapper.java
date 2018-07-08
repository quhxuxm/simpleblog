package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorFollower;

@Repository
@Mapper
public interface IAuthorFollowerPojoMapper {
    List<AuthorFollower> findAllByAuthorIdOrderByFollowDate(@Param("authorId") long authorId);

    void create(AuthorFollower follower);

    void delete(AuthorFollower follower);
}
