package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAuthenticationPojoMapper {
    Authentication findByTokenAndType(
            @Param("token")
                    String token,
            @Param("type")
                    Authentication.Type type);

    Authentication findOneById(
            @Param("id")
                    long id);

    void create(Authentication authentication);

    void update(Authentication authentication);

    Set<Authentication> findAllByAuthorId(
            @Param("authorId")
                    long authorId);
}
