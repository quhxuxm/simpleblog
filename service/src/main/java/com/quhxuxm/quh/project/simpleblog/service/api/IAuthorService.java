package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetail;
import com.quhxuxm.quh.project.simpleblog.service.dto.RegisterAuthorDTO;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

public interface IAuthorService {
    OptionalLong register(RegisterAuthorDTO registerAuthorDTO)
            throws ServiceException;

    Optional<AuthorDetail> login(String token, String password,
            Authentication.Type type) throws ServiceException;

    void assignTagToAuthor(Long authorId, Set<String> tags)
            throws ServiceException;

    void assignFollowerToAuthor(Long authorId, Long followerId)
            throws ServiceException;
}
