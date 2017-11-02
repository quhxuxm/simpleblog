package com.tongwen.repository.mapper;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorMapper {
    Author findAuthorByAuthenticationId(@Param("authenticationId") Long authenticationId);

    void createAuthor(@Param("authentication") Authentication authentication,
        @Param("author") Author author);
}
