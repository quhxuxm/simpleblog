package com.tongwen.service.api;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import com.tongwen.service.exception.ServiceException;

public interface IAuthorService {
    void register(String token, String password, String nickName,
            Authentication.Type type, String defaultAnthologyTitle,
            String defaultAnthologySummary) throws ServiceException;

    Author getAuthor(long authenticationId) throws ServiceException;
}
