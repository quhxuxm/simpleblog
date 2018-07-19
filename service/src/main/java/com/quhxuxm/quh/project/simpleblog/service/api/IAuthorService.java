package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

public interface IAuthorService {
    Long register(AuthorRegisterDTO registerAuthorDTO) throws ServiceException;

    AuthorDetailDTO findDetailById(Long id) throws ServiceException;

    AuthorDetailDTO loginByToken(String token) throws ServiceException;

    void assignTagsToAuthor(AuthorAssignTagsDTO authorAssignTagsDTO)
            throws ServiceException;

    void assignFollowerToAuthor(AuthorAssignFollowerDTO authorAssignFollowerDTO)
            throws ServiceException;

    AuthorAuthenticateDTO findForAuthenticate(String token)
            throws ServiceException;
}
