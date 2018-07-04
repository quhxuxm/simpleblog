package com.quhxuxm.quh.project.simpleblog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Author;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorAdditionalInfo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorDefaultAnthology;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAnthologyPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthenticationPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorAdditionalInfoPojoMapper;
import com.quhxuxm.quh.project.simpleblog.repository.pojo.IAuthorPojoMapper;
import com.quhxuxm.quh.project.simpleblog.service.api.ISecurityService;

@Service
class SecurityService implements ISecurityService {
    @Autowired
    private IAuthorPojoMapper authorPojoMapper;
    @Autowired
    private IAuthenticationPojoMapper authenticationPojoMapper;
    @Autowired
    private IAnthologyPojoMapper anthologyPojoMapper;
    @Autowired
    private IAuthorAdditionalInfoPojoMapper authorAdditionalInfoPojoMapper;

    @Transactional
    public void register(String token, String password, String nickName, Authentication.Type type) {
        AuthorAdditionalInfo authorAdditionalInfo = new AuthorAdditionalInfo();
        this.authorAdditionalInfoPojoMapper.create(authorAdditionalInfo);
        Author author = new Author();
        author.setNickName(nickName);
        author.setAdditionalInfoId(authorAdditionalInfo.getId());
        this.authorPojoMapper.create(author);
        Anthology defaultAnthology = new Anthology();
        defaultAnthology.setAuthorId(author.getId());
        this.anthologyPojoMapper.create(defaultAnthology);
        AuthorDefaultAnthology authorDefaultAnthology = new AuthorDefaultAnthology();
        authorDefaultAnthology.setAnthologyId(defaultAnthology.getId());
        authorDefaultAnthology.setAuthorId(author.getId());
        Authentication authentication = new Authentication();
        authentication.setToken(token);
        authentication.setPassword(password);
        authentication.setRegisterDate(new Date());
        authentication.setEnable(true);
        authentication.setType(type);
        authentication.setAuthorId(author.getId());
        this.authenticationPojoMapper.create(authentication);
    }
}
