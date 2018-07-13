package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.IAnthologyRepository;
import com.quhxuxm.quh.project.simpleblog.repository.IAuthorRepository;
import com.quhxuxm.quh.project.simpleblog.repository.IResourceRepository;
import com.quhxuxm.quh.project.simpleblog.repository.ITagRepository;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyTagRepository;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.*;

@Service
class AnthologyService implements IAnthologyService {
    private static final Logger logger = LoggerFactory
            .getLogger(AnthologyService.class);
    private IAnthologyRepository anthologyRepository;
    private IAnthologyTagRepository anthologyTagRepository;
    private IAuthorRepository authorRepository;
    private IResourceRepository resourceRepository;
    private ITagRepository tagRepository;

    AnthologyService(IAnthologyRepository anthologyRepository,
            IAnthologyTagRepository anthologyTagRepository,
            IAuthorRepository authorRepository,
            IResourceRepository resourceRepository,
            ITagRepository tagRepository) {
        this.anthologyRepository = anthologyRepository;
        this.anthologyTagRepository = anthologyTagRepository;
        this.authorRepository = authorRepository;
        this.resourceRepository = resourceRepository;
        this.tagRepository = tagRepository;
    }

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

    @Override
    public void bookmarkAnthology(AnthologyBookmarkDTO anthologyBookmarkDTO)
            throws ServiceException {
    }

    @Override
    public void praiseAnthology(AnthologyPraiseDTO anthologyPraiseDTO)
            throws ServiceException {
    }

    @Override
    public Optional<AnthologyDetailDTO> viewAnthology(
            AnthologyViewDTO anthologyViewDTO) throws ServiceException {
        return Optional.empty();
    }
}
