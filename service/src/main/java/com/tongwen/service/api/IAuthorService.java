package com.tongwen.service.api;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import com.tongwen.domain.AuthorAdditionalInfo;
import com.tongwen.service.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

public interface IAuthorService {
    void register(String token, String password, String nickName,
            Authentication.Type type, String defaultAnthologyTitle,
            String defaultAnthologySummary) throws ServiceException;

    Author getAuthenticatedAuthor(long authenticationId)
            throws ServiceException;

    Author getAuthor(long authorId) throws ServiceException;

    AuthorAdditionalInfo getAdditionalInfo(long authorId)
            throws ServiceException;

    boolean isNickNameExist(String nickName) throws ServiceException;
}
