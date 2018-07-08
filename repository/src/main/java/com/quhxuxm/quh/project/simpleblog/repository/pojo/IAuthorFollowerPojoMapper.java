package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorFollower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorFollowerPojoMapper {
    List<AuthorFollower> findAllByAuthorIdOrderByFollowDate(
            @Param("authorId")
                    long authorId);

    void create(AuthorFollower follower);

    void delete(AuthorFollower follower);
}
