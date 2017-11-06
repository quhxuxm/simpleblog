package com.tongwen.service.impl;

import com.tongwen.common.IConstant;
import com.tongwen.domain.Authentication;
import com.tongwen.domain.Role;
import com.tongwen.repository.mapper.IAuthenticationMapper;
import com.tongwen.repository.mapper.IRoleMapper;
import com.tongwen.service.api.IAuthenticationService;
import com.tongwen.service.exception.ServiceException;
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

    @Transactional
    @Override
    public Authentication authenticate(String token, Authentication.Type type)
            throws ServiceException {
        try {
            Authentication authentication = this.authenticationMapper
                    .findByTokenAndType(token, type);
            authentication.setLastLoginDate(new Date());
            this.authenticationMapper.update(authentication);
            return authentication;
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public boolean isTokenExist(String token) throws ServiceException {
        try {
            return this.authenticationMapper.isTokenExist(token);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public boolean isNickNameExist(String nickName) throws ServiceException {
        try {
            return this.authenticationMapper.isNickNameExist(nickName);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
