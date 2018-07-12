package com.quhxuxm.quh.project.simpleblog.service.impl;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.*;

@Service
class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private ITagRepository tagRepository;
    private IArticleTagRepository articleTagRepository;
    private IAnthologyParticipantRepository anthologyParticipantRepository;
    private IAuthorArticlePraiseRepository authorArticlePraiseRepository;
    private IAuthorRepository authorRepository;
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;
    private IAuthorArticleBookmarkRepository authorArticleBookmarkRepository;

    ArticleService(ITagRepository tagRepository,
            IArticleTagRepository articleTagRepository,
            IAnthologyParticipantRepository anthologyParticipantRepository,
            IAuthorArticlePraiseRepository authorArticlePraiseRepository,
            IAuthorRepository authorRepository,
            IArticleRepository articleRepository,
            IAnthologyRepository anthologyRepository,
            IAuthorArticleBookmarkRepository authorArticleBookmarkRepository) {
        this.tagRepository = tagRepository;
        this.articleTagRepository = articleTagRepository;
        this.anthologyParticipantRepository = anthologyParticipantRepository;
        this.authorArticlePraiseRepository = authorArticlePraiseRepository;
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
        this.authorArticleBookmarkRepository = authorArticleBookmarkRepository;
    }

    @Transactional
    @Override
    public OptionalLong saveArticle(CreateArticleDTO createArticleDTO)
            throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository
                    .getOne(createArticleDTO.getAnthologyId());
            Author author = this.authorRepository
                    .getOne(createArticleDTO.getAuthorId());
            boolean authorOwnAnthology = false;
            if (anthology.getAuthor().getId()
                    .equals(createArticleDTO.getAuthorId())) {
                authorOwnAnthology = true;
            }
            if (!authorOwnAnthology) {
                AnthologyParticipant.PK anthologyParticipantPk = new AnthologyParticipant.PK();
                anthologyParticipantPk.setAnthology(anthology);
                anthologyParticipantPk.setAuthor(author);
                if (!this.anthologyParticipantRepository
                        .existsByPkAndIsDeletedIsFalse(
                                anthologyParticipantPk)) {
                    logger.error(
                            "Author is not a participant of the anthology.");
                    throw new ServiceException(
                            "Author is not a participant of the anthology.");
                }
            }
            Article article = new Article();
            article.setTitle(createArticleDTO.getTitle());
            article.setContent(createArticleDTO.getContent());
            article.setCreateDate(new Date());
            article.setSummary(createArticleDTO.getSummary());
            article.setAnthology(anthology);
            this.articleRepository.save(article);
            Set<String> articleTags = createArticleDTO.getTags();
            articleTags.forEach(tagText -> {
                Tag tag = tagRepository.findByText(tagText);
                if (tag == null) {
                    tag = new Tag();
                    tag.setText(tagText);
                    tagRepository.save(tag);
                }
                ArticleTag articleTag = new ArticleTag();
                ArticleTag.PK articleTagPk = new ArticleTag.PK();
                articleTagPk.setArticle(article);
                articleTagPk.setTag(tag);
                articleTag.setPk(articleTagPk);
                articleTag.setSelected(true);
                articleTag.setWeight(
                        ICommonConstant.DefaultValue.ARTICLE_SELECTED_TAG_INIT_WEIGHT);
                this.articleTagRepository.save(articleTag);
            });
            return OptionalLong.of(article.getId());
        } catch (PersistenceException e) {
            logger.error("Fail to save article because of exception.", e);
            throw new ServiceException(
                    "Fail to save article because of exception.", e);
        }
    }

    @Transactional
    @Override
    public void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO)
            throws ServiceException {
        try {
            Article article = this.articleRepository
                    .getOne(articleAssignTagsDTO.getArticleId());
            if (!article.getAnthology().getAuthor().getId()
                    .equals(articleAssignTagsDTO.getAuthorId())) {
                logger.error(
                        "Can not assign tags to article because the author id not the owner of the article.");
                throw new ServiceException(
                        "Can not assign tags to article because the author id not the owner of the article.");
            }
            article.setUpdateDate(new Date());
            this.articleRepository.save(article);
            Set<ArticleTag> articleTags = this.articleTagRepository
                    .findAllByPkArticle(article);
            Set<String> tagTextsFromDb = new HashSet<>();
            articleTags.forEach(articleTag -> {
                Tag tag = articleTag.getPk().getTag();
                tagTextsFromDb.add(tag.getText());
            });
            Set<String> tagTextsFromDTO = new HashSet<>(
                    articleAssignTagsDTO.getTags());
            tagTextsFromDTO.removeAll(tagTextsFromDb);
            tagTextsFromDTO.forEach(text -> {
                Tag tag = new Tag();
                tag.setText(text);
                this.tagRepository.save(tag);
                ArticleTag articleTag = new ArticleTag();
                ArticleTag.PK articleTagPk = new ArticleTag.PK();
                articleTagPk.setArticle(article);
                articleTagPk.setTag(tag);
                articleTag.setSelected(true);
                articleTag.setWeight(
                        ICommonConstant.DefaultValue.ARTICLE_SELECTED_TAG_INIT_WEIGHT);
                this.articleTagRepository.save(articleTag);
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
    public void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(articleBookmarkDTO.getAuthorId());
            Article article = this.articleRepository
                    .getOne(articleBookmarkDTO.getArticleId());
            AuthorArticleBookmark.PK authorArticleBookmarkPk = new AuthorArticleBookmark.PK();
            authorArticleBookmarkPk.setArticle(article);
            authorArticleBookmarkPk.setAuthor(author);
            if (this.authorArticleBookmarkRepository
                    .existsById(authorArticleBookmarkPk)) {
                logger.debug(
                        "Same author will not able bookmark the same article again.");
                return;
            }
            AuthorArticleBookmark authorArticleBookmark = new AuthorArticleBookmark();
            authorArticleBookmark.setMarkDate(new Date());
            authorArticleBookmark.setPk(authorArticleBookmarkPk);
            this.authorArticleBookmarkRepository.save(authorArticleBookmark);
        } catch (PersistenceException e) {
            logger.error("Fail to bookmark article because of exception.", e);
            throw new ServiceException(
                    "Fail to bookmark article because of exception.", e);
        }
    }

    @Transactional
    @Override
    public void praiseArticle(ArticlePraiseDTO articlePraiseDTO)
            throws ServiceException {
        try {
            Author author = this.authorRepository
                    .getOne(articlePraiseDTO.getAuthorId());
            Article article = this.articleRepository
                    .getOne(articlePraiseDTO.getArticleId());
            AuthorArticlePraise.PK authorArticlePraisePk = new AuthorArticlePraise.PK();
            authorArticlePraisePk.setArticle(article);
            authorArticlePraisePk.setAuthor(author);
            if (this.authorArticlePraiseRepository
                    .existsById(authorArticlePraisePk)) {
                logger.debug(
                        "Same author will not able praise the same article again.");
                return;
            }
            AuthorArticlePraise authorArticlePraise = new AuthorArticlePraise();
            authorArticlePraise.setPraiseDate(new Date());
            authorArticlePraise.setPk(authorArticlePraisePk);
            this.authorArticlePraiseRepository.save(authorArticlePraise);
        } catch (PersistenceException e) {
            logger.error("Fail to praise article because of exception.", e);
            throw new ServiceException(
                    "Fail to praise article because of exception.", e);
        }
    }

    @Transactional
    @Override
    public Optional<ArticleDetailDTO> viewArticle(
            ArticleViewDTO articleViewDTO) {
        return Optional.empty();
    }
}
