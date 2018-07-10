package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.ISecurityService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
class SecurityService implements ISecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository;
    private IAuthenticationRepository authenticationRepository;
    private IAuthorRepository authorRepository;
    private IRoleRepository roleRepository;
    private IAnthologyRepository anthologyRepository;

    SecurityService(IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository, IAuthenticationRepository authenticationRepository, IAuthorRepository authorRepository,
            IRoleRepository roleRepository, IAnthologyRepository anthologyRepository) {
        this.authorDefaultAnthologyRepository = authorDefaultAnthologyRepository;
        this.authenticationRepository = authenticationRepository;
        this.authorRepository = authorRepository;
        this.roleRepository = roleRepository;
        this.anthologyRepository = anthologyRepository;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void register(String token, String password, String nickName, Authentication.Type type) throws ServiceException {
        Authentication authenticationInDb = null;
        try {
            authenticationInDb = this.authenticationRepository.findByTokenAndType(token, type);
        } catch (Exception e) {
            logger.error("Fail to register because of exception happen when check token and type with db.", e);
            throw new ServiceException("Fail to register because of exception happen when check token and type with db.", e);
        }
        if (authenticationInDb != null) {
            logger.error("Can not register because of token exist already, token = {}, type = {}.", token, type.name());
            throw new ServiceException(String.format("Can not register because of token exist already, token = %s, type = %s.", token, type.name()));
        }
        Authentication authentication = new Authentication();
        authentication.setToken(token);
        authentication.setType(type);
        authentication.setPassword(password);
        Author author = new Author();
        authentication.setAuthor(author);
        author.setNickName(nickName);
        Role authorRole = null;
        try {
            authorRole = this.roleRepository.findByName(ICommonConstant.RoleName.AUTHOR);
        } catch (Exception e) {
            logger.error("Fail to register because of exception happen when get author role from db.", e);
            throw new ServiceException("Fail to register because of exception happen when get author role from db.", e);
        }
        if (authorRole == null) {
            logger.error("Can not register because of author role not exist.");
            throw new ServiceException("Can not register because of author role not exist.");
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(authorRole);
        author.setRoles(roleSet);
        try {
            this.authorRepository.save(author);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save author.", e);
            throw new ServiceException("Fail to register because of exception when save author.");
        }
        try {
            this.authenticationRepository.save(authentication);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save authentication.", e);
            throw new ServiceException("Fail to register because of exception when save authentication.");
        }
        Anthology anthology = new Anthology();
        anthology.setAuthor(author);
        try {
            this.anthologyRepository.save(anthology);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save anthology.", e);
            throw new ServiceException("Fail to register because of exception when save anthology.");
        }
        AuthorDefaultAnthology authorDefaultAnthology = new AuthorDefaultAnthology();
        AuthorDefaultAnthology.PK authorDefaultAnthologyPK = new AuthorDefaultAnthology.PK();
        authorDefaultAnthologyPK.setAnthology(anthology);
        authorDefaultAnthologyPK.setAuthor(author);
        authorDefaultAnthology.setPk(authorDefaultAnthologyPK);
        try {
            this.authorDefaultAnthologyRepository.save(authorDefaultAnthology);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save author default anthology.", e);
            throw new ServiceException("Fail to register because of exception when save author default anthology.");
        }
    }
}
