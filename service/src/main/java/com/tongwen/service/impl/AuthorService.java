package com.tongwen.service.impl;

import com.tongwen.common.IConstant;
import com.tongwen.domain.*;
import com.tongwen.repository.mapper.IAnthologyMapper;
import com.tongwen.repository.mapper.IAuthenticationMapper;
import com.tongwen.repository.mapper.IAuthorMapper;
import com.tongwen.repository.mapper.IRoleMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AuthorService implements IAuthorService {
    private final IAnthologyService anthologyService;
    private final IAuthenticationMapper authenticationMapper;
    private final IRoleMapper roleMapper;
    private final IAuthorMapper authorMapper;

    @Autowired
    public AuthorService(IAuthenticationMapper authenticationMapper,
            IRoleMapper roleMapper, IAuthorMapper authorMapper,
            IAnthologyMapper anthologyMapper,
            IAnthologyService anthologyService) {
        this.authenticationMapper = authenticationMapper;
        this.roleMapper = roleMapper;
        this.authorMapper = authorMapper;
        this.anthologyService = anthologyService;
    }

    @Transactional
    @Override
    public void register(String token, String password, String nickName,
            Authentication.Type type, String defaultAnthologyTitle,
            String defaultAnthologySummary) throws ServiceException {
        if (this.authenticationMapper.isTokenExist(token)) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_TOKEN_EXIST);
        }
        if (this.authenticationMapper.isNickNameExist(nickName)) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_NICK_NAME_EXIST);
        }
        try {
            Authentication authentication = new Authentication();
            authentication.setToken(token);
            authentication.setPassword(password);
            authentication.setNickName(nickName);
            authentication.setType(type);
            authentication.setRegisterDate(new Date());
            authentication.setLastLoginDate(new Date());
            this.authenticationMapper.create(authentication);
            Role authorRole = this.roleMapper
                    .findRoleByName(IConstant.Role.ROLE_AUTHOR.name());
            this.authenticationMapper.assignRole(authentication, authorRole);
            Author author = new Author();
            this.authorMapper.create(author);
            this.authorMapper.assignAuthentication(authentication, author);
            Anthology defaultAnthology = new Anthology();
            defaultAnthology.setTitle(defaultAnthologyTitle);
            defaultAnthology.setSummary(defaultAnthologySummary);
            defaultAnthology.setCreateDate(new Date());
            defaultAnthology.setUpdateDate(defaultAnthology.getCreateDate());
            defaultAnthology.setAuthorId(author.getId());
            this.anthologyService.create(defaultAnthology);
            this.authorMapper.assignDefaultAnthology(defaultAnthology, author);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthor(long authenticationId) throws ServiceException {
        try {
            return this.authorMapper
                    .findAuthorByAuthenticationId(authenticationId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
