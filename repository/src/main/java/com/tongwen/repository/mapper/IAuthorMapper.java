package com.tongwen.repository.mapper;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorMapper {
    Author findAuthorByAuthenticationId(@Param("authenticationId") Long authenticationId);

    void create(Author author);

    void assignAuthentication(@Param("authentication") Authentication authentication,
        @Param("author") Author author);

    void assignDefaultAnthology(@Param("anthology") Anthology anthology,
        @Param("author") Author author);

    void updateDefaultAnthology(@Param("anthology") Anthology anthology,
        @Param("author") Author author);

    boolean isExist(@Param("id") long id);
}
