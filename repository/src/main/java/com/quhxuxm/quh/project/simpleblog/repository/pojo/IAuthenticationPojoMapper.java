package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import java.util.Date;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;

@Repository
public interface IAuthenticationPojoMapper {

    Authentication findOneByTokenAndType(@Param("token") String token, @Param("type") Authentication.Type type);

    Authentication findOneById(@Param("id") long id);

    void create(Authentication authentication);

    void updatePassword(@Param("id") Long id, @Param("password") String password);

    void updateLastLoginDate(@Param("id") Long id, @Param("lastLoginDate") Date lastLoginDate);

    void updateEnable(@Param("id") Long id, @Param("enable") boolean enable);

    Set<Authentication> findAllByAuthorId(@Param("authorId") long authorId);
}
