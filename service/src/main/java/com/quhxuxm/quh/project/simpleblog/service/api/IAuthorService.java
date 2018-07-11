package com.quhxuxm.quh.project.simpleblog.service.api;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

import java.util.Optional;
import java.util.OptionalLong;

public interface IAuthorService {
    OptionalLong register(
            AuthorRegisterDTO registerAuthorDTO) throws ServiceException;

    Optional<AuthorDetailDTO> login(
            AuthorLoginDTO authorLoginDTO) throws ServiceException;

    void assignTagsToAuthor(
            AuthorAssignTagsDTO authorAssignTagsDTO) throws ServiceException;

    void assignFollowerToAuthor(
            AuthorAssignFollowerDTO authorAssignFollowerDTO) throws
            ServiceException;
}
