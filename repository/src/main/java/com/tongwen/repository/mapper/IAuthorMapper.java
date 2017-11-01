package com.tongwen.repository.mapper;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import org.apache.ibatis.annotations.Param;

public interface IAuthorMapper {
    Author findAuthorByAuthentication(
            @Param("authenticationId")
                    Long authenticationId);
}
