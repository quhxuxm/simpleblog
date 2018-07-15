package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.*;
import java.util.stream.Collectors;

@Service
class AnthologyService implements IAnthologyService {
    private static final Logger logger = LoggerFactory
            .getLogger(AnthologyService.class);
    private IAnthologyRepository anthologyRepository;
    private IAnthologyTagRepository anthologyTagRepository;
    private IAuthorRepository authorRepository;
    private IResourceRepository resourceRepository;
    private ITagRepository tagRepository;
    private IAuthorAnthologyBookmarkRepository authorAnthologyBookmarkRepository;
    private IAuthorAnthologyPraiseRepository authorAnthologyPraiseRepository;
    private IAnthologyCommentRepository anthologyCommentRepository;
    private IAuthorService authorService;
    private IAuthorTagRepository authorTagRepository;

    AnthologyService(IAnthologyRepository anthologyRepository,
                     IAnthologyTagRepository anthologyTagRepository,
                     IAuthorRepository authorRepository,
                     IResourceRepository resourceRepository,
                     ITagRepository tagRepository, IAuthorAnthologyBookmarkRepository authorAnthologyBookmarkRepository, IAuthorAnthologyPraiseRepository authorAnthologyPraiseRepository, IAnthologyCommentRepository anthologyCommentRepository, IAuthorService authorService, IAuthorTagRepository authorTagRepository) {
        this.anthologyRepository = anthologyRepository;
        this.anthologyTagRepository = anthologyTagRepository;
        this.authorRepository = authorRepository;
        this.resourceRepository = resourceRepository;
        this.tagRepository = tagRepository;
        this.authorAnthologyBookmarkRepository = authorAnthologyBookmarkRepository;
        this.authorAnthologyPraiseRepository = authorAnthologyPraiseRepository;
        this.anthologyCommentRepository = anthologyCommentRepository;
        this.authorService = authorService;
        this.authorTagRepository = authorTagRepository;
    }

    @Transactional
    @Override
    public OptionalLong saveAnthology(CreateAnthologyDTO createAnthologyDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(createAnthologyDTO.getAuthorId());
            Anthology anthology = new Anthology();
            anthology.setAuthor(author);
            Resource coverImage = resourceRepository
                    .getOne(createAnthologyDTO.getCoverImageId());
            anthology.setCoverImage(coverImage);
            anthology.setCreateDate(new Date());
            if (createAnthologyDTO.getPublished()) {
                anthology.setPublished(createAnthologyDTO.getPublished());
                anthology.setPublishDate(new Date());
            }
            if (createAnthologyDTO.getShared()) {
                anthology.setShared(createAnthologyDTO.getShared());
                anthology.setSharedDate(new Date());
            }
            anthology.setSummary(createAnthologyDTO.getSummary());
            anthology.setTitle(createAnthologyDTO.getTitle());
            this.anthologyRepository.save(anthology);
            Set<String> tags = createAnthologyDTO.getTags();
            tags.forEach(tagText -> {
                Tag tag = this.tagRepository.findByText(tagText);
                if (tag == null) {
                    tag = new Tag();
                    tag.setText(tagText);
                    this.tagRepository.save(tag);
                }
                AnthologyTag.PK anthologyTagPk = new AnthologyTag.PK();
                AnthologyTag anthologyTag = new AnthologyTag();
                anthologyTagPk.setAnthology(anthology);
                anthologyTagPk.setTag(tag);
                anthologyTag.setPk(anthologyTagPk);
                this.anthologyTagRepository.save(anthologyTag);
            });
            return OptionalLong.of(anthology.getId());
        } catch (PersistenceException e) {
            logger.error("Fail to save anthology because of exception.", e);
            throw new ServiceException(
                    "Fail to save anthology because of exception.", e);
        }
    }

    @Transactional
    @Override
    public void assignTagsToAnthology(
            AnthologyAssignTagsDTO anthologyAssignTagsDTO)
            throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository
                    .getOne(anthologyAssignTagsDTO.getAnthologyId());
            if (!anthology.getAuthor().getId()
                    .equals(anthologyAssignTagsDTO.getAuthorId())) {
                logger.error(
                        "Can not assign tags to anthology because the author id not the owner of the anthology.");
                throw new ServiceException(
                        "Can not assign tags to anthology because the author id not the owner of the anthology.");
            }
            anthology.setUpdateDate(new Date());
            this.anthologyRepository.save(anthology);
            Set<AnthologyTag> anthologyTags = this.anthologyTagRepository
                    .findAllByPkAnthology(anthology);
            Set<String> tagTextsFromDb = new HashSet<>();
            anthologyTags.forEach(anthologyTag -> {
                Tag tag = anthologyTag.getPk().getTag();
                tagTextsFromDb.add(tag.getText());
            });
            Set<String> tagTextsFromDTO = new HashSet<>(
                    anthologyAssignTagsDTO.getTags());
            tagTextsFromDTO.removeAll(tagTextsFromDb);
            tagTextsFromDTO.forEach(text -> {
                Tag tag = new Tag();
                tag.setText(text);
                this.tagRepository.save(tag);
                AnthologyTag anthologyTag = new AnthologyTag();
                AnthologyTag.PK anthologyTagPk = new AnthologyTag.PK();
                anthologyTagPk.setAnthology(anthology);
                anthologyTagPk.setTag(tag);
                anthologyTag.setSelected(true);
                anthologyTag.setWeight(
                        ICommonConstant.DefaultValue.ANTHOLOGY_SELECTED_TAG_INIT_WEIGHT);
                this.anthologyTagRepository.save(anthologyTag);
            });
        } catch (PersistenceException e) {
            logger.error("Fail to assign article tags because of exception.",
                    e);
            throw new ServiceException(
                    "Fail to assign article tags because of exception.", e);
        }
    }

    @Transactional
    @Override
    public void bookmarkAnthology(AnthologyBookmarkDTO anthologyBookmarkDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(anthologyBookmarkDTO.getAuthorId());
            Anthology anthology = this.anthologyRepository
                    .getOne(anthologyBookmarkDTO.getAnthologyId());
            AuthorAnthologyBookmark.PK authorAnthologyBookmarkPk = new AuthorAnthologyBookmark.PK();
            authorAnthologyBookmarkPk.setAnthology(anthology);
            authorAnthologyBookmarkPk.setAuthor(author);
            if (this.authorAnthologyBookmarkRepository
                    .existsById(authorAnthologyBookmarkPk)) {
                logger.debug(
                        "Same author will not able bookmark the same article again.");
                return;
            }
            AuthorAnthologyBookmark authorAnthologyBookmark = new AuthorAnthologyBookmark();
            authorAnthologyBookmark.setMarkDate(new Date());
            authorAnthologyBookmark.setPk(authorAnthologyBookmarkPk);
            this.authorAnthologyBookmarkRepository.save(authorAnthologyBookmark);
            this.increaseAuthorTagWeightAccordingToAnthologyTags(author, anthology);
        } catch (PersistenceException e) {
            logger.error("Fail to bookmark anthology because of exception.", e);
            throw new ServiceException(
                    "Fail to bookmark anthology because of exception.", e);
        }
    }

    @Transactional
    @Override
    public void praiseAnthology(AnthologyPraiseDTO anthologyPraiseDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(anthologyPraiseDTO.getAuthorId());
            Anthology anthology = this.anthologyRepository
                    .getOne(anthologyPraiseDTO.getAnthologyId());
            AuthorAnthologyPraise.PK authorAnthologyPraisePk = new AuthorAnthologyPraise.PK();
            authorAnthologyPraisePk.setAnthology(anthology);
            authorAnthologyPraisePk.setAuthor(author);
            if (this.authorAnthologyPraiseRepository
                    .existsById(authorAnthologyPraisePk)) {
                logger.debug(
                        "Same author will not able praise the same article again.");
                return;
            }
            AuthorAnthologyPraise authorAnthologyPraise = new AuthorAnthologyPraise();
            authorAnthologyPraise.setPraiseDate(new Date());
            authorAnthologyPraise.setPk(authorAnthologyPraisePk);
            this.authorAnthologyPraiseRepository.save(authorAnthologyPraise);
            this.increaseAuthorTagWeightAccordingToAnthologyTags(author, anthology);
        } catch (PersistenceException e) {
            logger.error("Fail to praise anthology because of exception.", e);
            throw new ServiceException(
                    "Fail to praise anthology because of exception.", e);
        }
    }

    @Transactional
    @Override
    public Optional<AnthologyDetailDTO> viewAnthology(
            AnthologyViewDTO anthologyViewDTO) throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository
                    .getOne(anthologyViewDTO.getAnthologyId());
            AnthologyDetailDTO result = new AnthologyDetailDTO();
            result.setAnthologyId(anthology.getId());
            result.setSummary(anthology.getSummary());
            if (anthology.getAuthor().getIconImage() != null) {
                result.setAuthorIconImageId(
                        anthology.getAuthor().getIconImage()
                                .getId());
            }
            if (anthology.getCoverImage() != null) {
                result.setCoverImageId(
                        anthology.getCoverImage().getId());
            }
            result.setTitle(anthology.getTitle());
            result.setAuthorNickName(
                    anthology.getAuthor().getNickName());
            result.setAuthorId(anthology.getAuthor().getId());
            result.setBookmarkNumber(this.authorAnthologyBookmarkRepository
                    .countByPkAnthology(anthology));
            result.setCommentNumber(
                    this.anthologyCommentRepository.countByAnthology(anthology));
            result.setPraiseNumber(this.authorAnthologyPraiseRepository
                    .countByPkAnthology(anthology));
            return Optional.of(result);
        } catch (PersistenceException e) {
            throw new ServiceException(
                    "Can not view article because of exception.");
        }
    }

    @Transactional
    @Override
    public void increaseAuthorTagWeightAccordingToAnthologyTags(Author author, Anthology anthology) throws ServiceException {
        try {
            Set<AnthologyTag> anthologyTags = this.anthologyTagRepository
                    .findAllByPkAnthology(anthology);
            Set<Tag> tagsFromAnthology = anthologyTags.stream().map(tag -> {
                return tag.getPk().getTag();
            }).collect(Collectors.toSet());
            Set<AuthorTag> authorTags = this.authorTagRepository
                    .findAllByPkAuthor(author);
            Set<Tag> tagsFromAuthor = authorTags.stream().map(tag -> {
                return tag.getPk().getTag();
            }).collect(Collectors.toSet());
            tagsFromAuthor.addAll(tagsFromAnthology);
            AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
            authorAssignTagsDTO.setAuthorId(author.getId());
            authorAssignTagsDTO.setSelect(false);
            tagsFromAuthor.forEach(tag -> {
                authorAssignTagsDTO.getTags().add(tag.getText());
            });
            this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
        } catch (PersistenceException e) {
            logger.error(
                    "Can not increase author tag weight according to anthology because of exception.",
                    e);
            throw new ServiceException(
                    "Can not increase author tag weight according to anthology because of exception.",
                    e);
        }
    }
}
