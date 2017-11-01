package com.tongwen.repository.mapper;

import com.tongwen.domain.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorMapper {
    Author findAuthorByAuthenticationId(@Param("authenticationId") Long authenticationId);
}
