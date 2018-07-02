package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.repository.IAuthenticationMapper;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthenticationService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AuthenticationService implements IAuthenticationService {
    private final IAuthenticationMapper authenticationMapper;

    @Autowired
    public AuthenticationService(IAuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public Authentication authenticate(String token, Authentication.Type type)
            throws ServiceException {
        try {
            Authentication authentication = this.authenticationMapper
                    .findByTokenAndType(token, type);
            if (authentication == null) {
                return null;
            }
            authentication.setLastLoginDate(new Date());
            this.authenticationMapper.update(authentication);
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isTokenExist(String token) throws ServiceException {
        try {
            return this.authenticationMapper.isTokenExist(token);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
