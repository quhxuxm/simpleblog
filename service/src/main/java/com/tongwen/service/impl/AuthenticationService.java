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

    @Transactional(readOnly = true)
    @Override
    public Authentication authenticate(String token, Authentication.Type type)
            throws ServiceException {
        try {
            return this.authenticationMapper.findByTokenAndType(token, type);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
