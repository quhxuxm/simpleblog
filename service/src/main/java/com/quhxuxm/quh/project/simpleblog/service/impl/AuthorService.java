package com.quhxuxm.quh.project.simpleblog.service.impl;
import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
class AuthorService implements IAuthorService {
    private static final Logger logger = LoggerFactory.getLogger(
            AuthorService.class);
    private IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository;
    private IAuthenticationRepository authenticationRepository;
    private IAuthorRepository authorRepository;
    private IRoleRepository roleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorTagRepository authorTagRepository;
    private ITagRepository tagRepository;
    private IAuthorFollowerRepository authorFollowerRepository;

    AuthorService(
            IAuthorDefaultAnthologyRepository authorDefaultAnthologyRepository,
            IAuthenticationRepository authenticationRepository,
            IAuthorRepository authorRepository, IRoleRepository roleRepository,
            IAnthologyRepository anthologyRepository,
            IAuthorTagRepository authorTagRepository,
            ITagRepository tagRepository,
            IAuthorFollowerRepository authorFollowerRepository) {
        this.authorDefaultAnthologyRepository =
                authorDefaultAnthologyRepository;
        this.authenticationRepository = authenticationRepository;
        this.authorRepository = authorRepository;
        this.roleRepository = roleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorTagRepository = authorTagRepository;
        this.tagRepository = tagRepository;
        this.authorFollowerRepository = authorFollowerRepository;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public OptionalLong register(
            AuthorRegisterDTO authorRegisterDTO) throws ServiceException {
        Authentication authenticationInDb = null;
        try {
            authenticationInDb = this.authenticationRepository
                    .findByTokenAndType(
                    authorRegisterDTO.getToken(), authorRegisterDTO.getType());
            if (authenticationInDb != null) {
                logger.error(
                        "Can not register because of token exist already, " +
                                "token = {}, type = {}.",
                        authorRegisterDTO.getToken(),
                        authorRegisterDTO.getType().name());
                return OptionalLong.empty();
            }
            Authentication authentication = new Authentication();
            authentication.setToken(authorRegisterDTO.getToken());
            authentication.setType(authorRegisterDTO.getType());
            authentication.setPassword(authorRegisterDTO.getPassword());
            Author author = new Author();
            authentication.setAuthor(author);
            author.setNickName(authorRegisterDTO.getNickName());
            Role authorRole = this.roleRepository.findByName(
                    ICommonConstant.RoleName.AUTHOR);
            if (authorRole == null) {
                logger.error(
                        "Can not register because of author role not exist.");
                return OptionalLong.empty();
            }
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(authorRole);
            author.setRoles(roleSet);
            this.authorRepository.save(author);
            this.authenticationRepository.save(authentication);
            Anthology anthology = new Anthology();
            anthology.setAuthor(author);
            this.anthologyRepository.save(anthology);
            AuthorDefaultAnthology authorDefaultAnthology = new
                    AuthorDefaultAnthology();
            AuthorDefaultAnthology.PK authorDefaultAnthologyPK = new
                    AuthorDefaultAnthology.PK();
            authorDefaultAnthologyPK.setAnthology(anthology);
            authorDefaultAnthologyPK.setAuthor(author);
            authorDefaultAnthology.setPk(authorDefaultAnthologyPK);
            this.authorDefaultAnthologyRepository.save(authorDefaultAnthology);
            return OptionalLong.of(author.getId());
        } catch (Exception e) {
            logger.error(
                    "Fail to register because of exception when save author "
                            + "default anthology.",
                    e);
            throw new ServiceException(
                    "Fail to register because of exception when save author "
                            + "default anthology.");
        }
    }

    @Override
    public Optional<AuthorDetailDTO> login(
            AuthorLoginDTO authorLoginDTO) throws ServiceException {
        try {
            Authentication authentication = this.authenticationRepository
                    .findByTokenAndPasswordAndType(
                    authorLoginDTO.getToken(), authorLoginDTO.getPassword(),
                    authorLoginDTO.getType());
            if (authentication == null) {
                logger.error(
                        "Can not login because of authentication not exit.");
                return Optional.empty();
            }
            AuthorDetailDTO result = new AuthorDetailDTO();
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
            result.setAnthologyNumber(
                    authentication.getAuthor().getAdditionalInfo()
                            .getAnthologyNumber());
            result.setArticleNumber(
                    authentication.getAuthor().getAdditionalInfo()
                            .getArticleNumber());
            result.setCommentNumber(
                    authentication.getAuthor().getAdditionalInfo()
                            .getCommentNumber());
            result.setFollowedByNumber(
                    authentication.getAuthor().getAdditionalInfo()
                            .getFollowedByNumber());
            authentication.setLastLoginDate(new Date());
            this.authenticationRepository.save(authentication);
            return Optional.of(result);
        } catch (Exception e) {
            logger.error("Can not login because of the exception.", e);
            throw new ServiceException(
                    "Can not login because of the exception.");
        }
    }

    @Transactional
    @Override
    public void assignTagsToAuthor(
            AuthorAssignTagsDTO authorAssignTagsDTO) throws ServiceException {
        try {
            Author authorFromDb = this.authorRepository.getOne(
                    authorAssignTagsDTO.getAuthorId());
            authorAssignTagsDTO.getTags().forEach(tagText -> {
                Tag tag = this.tagRepository.findByText(tagText);
                if (tag == null) {
                    Tag newTag = new Tag();
                    newTag.setText(tagText);
                    this.tagRepository.save(newTag);
                    tag = newTag;
                }
                AuthorTag.PK authorTagPk = new AuthorTag.PK();
                authorTagPk.setAuthor(authorFromDb);
                authorTagPk.setTag(tag);
                this.authorTagRepository.findById(authorTagPk).ifPresentOrElse(
                        authorTag -> {
                            logger.debug(
                                    "Will not create new author tag because "
                                            + "it exist already.");
                        }, () -> {
                            AuthorTag authorTag = new AuthorTag();
                            authorTag.setPk(authorTagPk);
                            authorTag.setSelected(true);
                            authorTag.setWeight(
                                    ICommonConstant.DefaultValue
                                            .AUTHOR_SELECTED_TAG_INIT_WEIGHT);
                            this.authorTagRepository.save(authorTag);
                        });
            });
        } catch (Exception e) {
            logger.error("Can not assign tog to author because of exception.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because of exception.", e);
        }
    }

    @Override
    public void assignFollowerToAuthor(
            AuthorAssignFollowerDTO authorAssignFollowerDTO) throws
            ServiceException {
        try {
            Author author = this.authorRepository.getOne(
                    authorAssignFollowerDTO.getAuthorId());
            Author follower = this.authorRepository.getOne(
                    authorAssignFollowerDTO.getFollowerId());
            AuthorFollower.PK authorFollowerPk = new AuthorFollower.PK();
            authorFollowerPk.setAuthor(author);
            authorFollowerPk.setFollower(follower);
            this.authorFollowerRepository.findById(
                    authorFollowerPk).ifPresentOrElse(authorFollower -> {
                logger.debug(
                        "Will not create new author follower because it " +
                                "exist" + " already.");
            }, () -> {
                AuthorFollower authorFollower = new AuthorFollower();
                authorFollower.setPk(authorFollowerPk);
                authorFollower.setFollowDate(new Date());
                this.authorFollowerRepository.save(authorFollower);
            });
        } catch (Exception e) {
            logger.error("Can not assign tog to author because of exception.",
                    e);
            throw new ServiceException(
                    "Can not assign tog to author because of exception.", e);
        }
    }
}
