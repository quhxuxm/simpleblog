package com.quhxuxm.quh.project.simpleblog.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Author;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorDefaultAnthology;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorRole;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Role;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAnthologyPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthenticationPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorAdditionalInfoPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorDefaultAnthologyPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorRolePojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IRolePojoMapper;
import com.quhxuxm.quh.project.simpleblog.service.api.ISecurityService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;

@Service
class SecurityService implements ISecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    @Autowired
    private IAuthorPojoMapper authorPojoMapper;
    @Autowired
    private IAuthenticationPojoMapper authenticationPojoMapper;
    @Autowired
    private IAnthologyPojoMapper anthologyPojoMapper;
    @Autowired
    private IAuthorAdditionalInfoPojoMapper authorAdditionalInfoPojoMapper;
    @Autowired
    private IAuthorDefaultAnthologyPojoMapper authorDefaultAnthologyPojoMapper;
    @Autowired
    private IRolePojoMapper rolePojoMapper;
    @Autowired
    private IAuthorRolePojoMapper authorRolePojoMapper;

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void register(String token, String password, String nickName, Authentication.Type type)
            throws ServiceException {
        logger.debug("Begin to register author with token = {}, password = {}, nicke name = {}", token, password,
                nickName);
        try {
            if (this.authenticationPojoMapper.isTokenExist(token, type)) {
                logger.error("Toekn [{}] exist for type [{}].", token, type.name());
                throw new ServiceException("Toekn [" + token + "] exist for type [" + type.name() + "].");
            }
            if (this.authorPojoMapper.isNickNameExist(nickName)) {
                logger.error("Nick name [{}] exist.", nickName);
                throw new ServiceException("Nick name [" + nickName + "] exist.");
            }
            AuthorAdditionalInfo authorAdditionalInfo = new AuthorAdditionalInfo();
            this.authorAdditionalInfoPojoMapper.create(authorAdditionalInfo);
            logger.debug("Success to prepare author additional infor pojo, id={}", authorAdditionalInfo.getId());
            Author author = new Author();
            author.setNickName(nickName);
            author.setAdditionalInfoId(authorAdditionalInfo.getId());
            this.authorPojoMapper.create(author);
            logger.debug("Success to prepare author pojo, id={}", author.getId());
            Role rolePojo = this.rolePojoMapper.findOneByName(ICommonConstant.RoleName.AUTHOR);
            if (rolePojo == null) {
                logger.error("The author role not exist in the database");
                throw new ServiceException("The author role not exist in the database");
            }
            logger.debug("Success to find role {}, id={}", ICommonConstant.RoleName.AUTHOR, rolePojo.getId());
            AuthorRole authorRolePojo = new AuthorRole();
            authorRolePojo.setAuthorId(author.getId());
            authorRolePojo.setRoleId(rolePojo.getId());
            this.authorRolePojoMapper.create(authorRolePojo);
            logger.debug("Success to prepare author role pojo, author id={}, role id ={}", authorRolePojo.getAuthorId(),
                    authorRolePojo.getRoleId());
            Anthology anthologyPojo = new Anthology();
            anthologyPojo.setAuthorId(author.getId());
            this.anthologyPojoMapper.create(anthologyPojo);
            logger.debug("Success to prepare anthology pojo, id ={}", anthologyPojo.getId());
            AuthorDefaultAnthology authorDefaultAnthology = new AuthorDefaultAnthology();
            authorDefaultAnthology.setAnthologyId(anthologyPojo.getId());
            authorDefaultAnthology.setAuthorId(author.getId());
            this.authorDefaultAnthologyPojoMapper.create(authorDefaultAnthology);
            logger.debug("Success to prepare author default anthology pojo, author id ={}, default anthology id = {}",
                    authorDefaultAnthology.getAuthorId(), authorDefaultAnthology.getAnthologyId());
            Authentication authentication = new Authentication();
            authentication.setToken(token);
            authentication.setPassword(password);
            authentication.setRegisterDate(new Date());
            authentication.setEnable(true);
            authentication.setType(type);
            authentication.setAuthorId(author.getId());
            this.authenticationPojoMapper.create(authentication);
            logger.debug("Success to prepare authenticate pojo, author id ={}, id = {}", authentication.getAuthorId(),
                    authentication.getId());
        } catch (Exception e) {
            logger.error("Fail to register author because of exception.", e);
            throw new ServiceException(e);
        }
    }
}
