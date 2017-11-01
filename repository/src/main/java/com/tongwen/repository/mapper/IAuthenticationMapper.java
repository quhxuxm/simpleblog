package com.tongwen.repository.mapper;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import com.tongwen.domain.Role;
import org.apache.ibatis.annotations.Param;

public interface IAuthenticationMapper {
    Authentication findAuthenticateByToken(
            @Param("token")
                    String token);

    Authentication findAuthenticationById(
            @Param("id")
                    Long id);

    void createAuthentication(
            @Param("authentication")
                    Authentication authentication);

    void assignRoleToAuthentication(
            @Param("authentication")
                    Authentication authentication,
            @Param("authentication")
                    Role role);
}
