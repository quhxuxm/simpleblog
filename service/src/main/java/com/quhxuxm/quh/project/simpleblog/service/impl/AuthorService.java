package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.tongwen.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.IAnthologyMapper;
import com.quhxuxm.quh.project.simpleblog.repository.IAuthenticationMapper;
import com.quhxuxm.quh.project.simpleblog.repository.IAuthorMapper;
import com.quhxuxm.quh.project.simpleblog.repository.IRoleMapper;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
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

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void register(String token, String password, String nickName,
                         Authentication.Type type, String defaultAnthologyTitle,
                         String defaultAnthologySummary) throws ServiceException {
        if (this.authenticationMapper.isTokenExist(token)) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_TOKEN_EXIST);
        }
        if (this.authorMapper.isNickNameExist(nickName)) {
            throw new ServiceException(
                    ServiceException.Code.AUTHENTICATION_NICK_NAME_EXIST);
        }
        try {
            Authentication authentication = new Authentication();
            authentication.setToken(token);
            authentication.setPassword(password);
            authentication.setType(type);
            authentication.setRegisterDate(new Date());
            authentication.setLastLoginDate(new Date());
            this.authenticationMapper.create(authentication);
            Role authorRole = this.roleMapper
                    .findRoleByName(IConstant.Role.ROLE_AUTHOR.name());
            this.authenticationMapper.assignRole(authentication, authorRole);
            Author author = new Author();
            AuthorAdditionalInfo authorAdditionalInfo = new AuthorAdditionalInfo();
            this.authorMapper.createAdditionalInfo(authorAdditionalInfo);
            author.setAdditionalInfoId(authorAdditionalInfo.getId());
            author.setNickName(nickName);
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
    public Author getAuthenticatedAuthor(long authenticationId)
            throws ServiceException {
        try {
            return this.authorMapper
                    .findAuthorByAuthenticationId(authenticationId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthor(long authorId) throws ServiceException {
        try {
            return this.authorMapper.getAuthorById(authorId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorAdditionalInfo getAdditionalInfo(long authorId)
            throws ServiceException {
        try {
            return this.authorMapper.getAdditionalInfo(authorId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isNickNameExist(String nickName) throws ServiceException {
        try {
            return this.authorMapper.isNickNameExist(nickName);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
