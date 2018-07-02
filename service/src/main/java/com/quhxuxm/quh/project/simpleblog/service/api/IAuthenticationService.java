package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;

public interface IAuthenticationService {
    Authentication authenticate(String token, Authentication.Type type)
            throws ServiceException;

    boolean isTokenExist(String token) throws ServiceException;
}
