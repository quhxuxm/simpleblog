package com.tongwen.repository.mapper;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationMapper {
    Authentication findByTokenAndType(
            @Param("token")
                    String token,
            @Param("type")
                    Authentication.Type type);

    Authentication getOne(
            @Param("id")
                    long id);

    void create(Authentication authentication);

    void update(Authentication authentication);

    void assignRole(
            @Param("authentication")
                    Authentication authentication,
            @Param("role")
                    Role role);

    boolean isTokenExist(
            @Param("token")
                    String token);
}
