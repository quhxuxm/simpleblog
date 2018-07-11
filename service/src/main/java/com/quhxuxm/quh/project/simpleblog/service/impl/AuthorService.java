package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

@Service
class AuthorService implements IAuthorService {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorService.class);
    private IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository;
    private IAuthenticationRepository authenticationRepository;
    private IAuthorRepository authorRepository;
    private IRoleRepository roleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorTagRepository authorTagRepository;
    private ITagRepository tagRepository;

    AuthorService(
            IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository,
            IAuthenticationRepository authenticationRepository,
            IAuthorRepository authorRepository, IRoleRepository roleRepository,
            IAnthologyRepository anthologyRepository,
            IAuthorTagRepository authorTagRepository,
            ITagRepository tagRepository) {
        this.authorDefaultAnthologyRepository = authorDefaultAnthologyRepository;
        this.authenticationRepository = authenticationRepository;
        this.authorRepository = authorRepository;
        this.roleRepository = roleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorTagRepository = authorTagRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public OptionalLong register(String token, String password, String nickName,
            Authentication.Type type) throws ServiceException {
        Authentication authenticationInDb = null;
        try {
            authenticationInDb = this.authenticationRepository
                    .findByTokenAndType(token, type);
        } catch (Exception e) {
            logger.error(
                    "Fail to register because of exception happen when check "
                            + "token and type with db.", e);
            throw new ServiceException(
                    "Fail to register because of exception happen when check token and type with db.",
                    e);
        }
        if (authenticationInDb != null) {
            logger.error(
                    "Can not register because of token exist already, token "
                            + "=" + " {}, type = {}.", token, type.name());
            throw new ServiceException(String.format(
                    "Can not register because of token exist already, token "
                            + "=" + " %s, type = %s.", token, type.name()));
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
            authorRole = this.roleRepository
                    .findByName(ICommonConstant.RoleName.AUTHOR);
        } catch (Exception e) {
            logger.error(
                    "Fail to register because of exception happen when get "
                            + "author role from db.", e);
            throw new ServiceException(
                    "Fail to register because of exception happen when get "
                            + "author role from db.", e);
        }
        if (authorRole == null) {
            logger.error("Can not register because of author role not exist.");
            throw new ServiceException(
                    "Can not register because of author role not exist.");
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(authorRole);
        author.setRoles(roleSet);
        try {
            this.authorRepository.save(author);
        } catch (Exception e) {
            logger.error(
                    "Fail to register because of exception when save author.",
                    e);
            throw new ServiceException(
                    "Fail to register because of exception when save author.");
        }
        try {
            this.authenticationRepository.save(authentication);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save "
                    + "authentication.", e);
            throw new ServiceException(
                    "Fail to register because of exception when save "
                            + "authentication.");
        }
        Anthology anthology = new Anthology();
        anthology.setAuthor(author);
        try {
            this.anthologyRepository.save(anthology);
        } catch (Exception e) {
            logger.error("Fail to register because of exception when save "
                    + "anthology.", e);
            throw new ServiceException(
                    "Fail to register because of exception when save "
                            + "anthology.");
        }
        AuthorDefaultAnthology authorDefaultAnthology = new AuthorDefaultAnthology();
        AuthorDefaultAnthology.PK authorDefaultAnthologyPK = new AuthorDefaultAnthology.PK();
        authorDefaultAnthologyPK.setAnthology(anthology);
        authorDefaultAnthologyPK.setAuthor(author);
        authorDefaultAnthology.setPk(authorDefaultAnthologyPK);
        try {
            this.authorDefaultAnthologyRepository.save(authorDefaultAnthology);
        } catch (Exception e) {
            logger.error(
                    "Fail to register because of exception when save author "
                            + "default anthology.", e);
            throw new ServiceException(
                    "Fail to register because of exception when save author "
                            + "default anthology.");
        }
        return OptionalLong.of(author.getId());
    }

    @Override
    public Optional<AuthorDetail> login(String token, String password,
            Authentication.Type type) throws ServiceException {
        Authentication authentication = null;
        try {
            authentication = this.authenticationRepository
                    .findByTokenAndPasswordAndType(token, password, type);
        } catch (Exception e) {
            logger.error("Can not login because of the exception.", e);
            throw new ServiceException(
                    "Can not login because of the exception.");
        }
        if (authentication == null) {
            logger.error("Can not login because of authentication not exit.");
            throw new ServiceException(
                    "Can not login because of authentication not exit.");
        }
        AuthorDetail result = new AuthorDetail();
        result.setAuthorId(authentication.getAuthor().getId());
        result.setNickName(authentication.getAuthor().getNickName());
        result.setLastLoginDate(authentication.getLastLoginDate());
        result.setRegisterDate(authentication.getRegisterDate());
        result.setAuthenticationToken(authentication.getToken());
        result.setAuthenticationType(authentication.getType());
        authentication.getAuthor().getRoles().forEach(role -> {
            result.getRoles().add(role.getName());
        });
        Set<AuthorTag> authorTags = this.authorTagRepository
                .findAllByPkAuthorAndIsSelectedIsTrue(
                        authentication.getAuthor());
        authorTags.forEach(authorTag -> {
            result.getTags().add(authorTag.getPk().getTag().getText());
        });
        result.setAnthologyNumber(authentication.getAuthor().getAdditionalInfo()
                .getAnthologyNumber());
        result.setArticleNumber(authentication.getAuthor().getAdditionalInfo()
                .getArticleNumber());
        result.setCommentNumber(authentication.getAuthor().getAdditionalInfo()
                .getCommentNumber());
        result.setFollowedByNumber(
                authentication.getAuthor().getAdditionalInfo()
                        .getFollowedByNumber());
        return Optional.of(result);
    }

    @Transactional
    @Override
    public void assignTagToAuthor(Long authorId, Set<String> tagTexts)
            throws ServiceException {
        try {
            Author authorFromDb = this.authorRepository.getOne(authorId);
            tagTexts.forEach(tagText -> {
                Tag tag = this.tagRepository.findByText(tagText);
                if (tag == null) {
                    Tag newTag = new Tag();
                    newTag.setText(tagText);
                    this.tagRepository.save(newTag);
                    tag = newTag;
                }
                AuthorTag authorTag = new AuthorTag();
                AuthorTag.PK authorTagPk = new AuthorTag.PK();
                authorTagPk.setAuthor(authorFromDb);
                authorTagPk.setTag(tag);
                authorTag.setPk(authorTagPk);
                authorTag.setSelected(true);
                authorTag.setWeight(1d);
                this.authorTagRepository.save(authorTag);
            });
        } catch (EntityNotFoundException e) {
            logger.error(
                    "Can not assign tog to author because author not exist.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because author not exist.",
                    e);
        }
    }
}
