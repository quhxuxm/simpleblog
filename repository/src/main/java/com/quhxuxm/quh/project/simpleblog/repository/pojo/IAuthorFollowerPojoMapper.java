package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorFollower;

public interface IAuthorFollowerPojoMapper {
    List<AuthorFollower> findAllByAuthorIdOrderByFollowDate(@Param("authorId") long authorId);

    void create(AuthorFollower follower);

    void delete(AuthorFollower follower);
}
