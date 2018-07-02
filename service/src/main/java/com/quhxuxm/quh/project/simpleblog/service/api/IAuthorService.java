package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;

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
