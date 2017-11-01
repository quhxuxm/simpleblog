package com.tongwen.repository.mapper;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationMapper {
    Authentication findAuthenticateByToken(@Param("token") String token);

    Authentication findAuthenticationById(@Param("id") Long id);

    void createAuthentication(Authentication authentication);

    void assignRoleToAuthentication(@Param("authentication") Authentication authentication,
        @Param("role") Role role);
}
