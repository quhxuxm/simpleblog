package com.tongwen.service.api;

import com.tongwen.domain.Authentication;
import com.tongwen.domain.Role;
import com.tongwen.service.exception.ServiceException;

public interface IAuthenticationService {
    Authentication authenticate(String token, Authentication.Type type)
            throws ServiceException;
}
