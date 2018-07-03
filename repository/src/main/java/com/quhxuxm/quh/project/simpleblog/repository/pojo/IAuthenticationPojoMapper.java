package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationPojoMapper {
    Authentication findByTokenAndType(@Param("token") String token, @Param("type") Authentication.Type type);

    Authentication findOneById(@Param("id") long id);

    void create(Authentication authentication);

    void update(Authentication authentication);

    void assignRole(@Param("authenticationId") Long authenticationId, @Param("roleId") Long roleId);

    boolean isTokenExist(@Param("token") String token);

    void assignAuthentication(@Param("authenticationId") long authenticationId, @Param("authorId") long authorId);
}
