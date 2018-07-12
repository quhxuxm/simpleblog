package com.quhxuxm.quh.project.simpleblog.service.impl;
import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.quhxuxm.quh.project.simpleblog.repository.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory.getLogger(
            ArticleService.class);
    private ITagRepository tagRepository;
    private IArticleTagRepository articleTagRepository;
    private IAnthologyParticipantRepository anthologyParticipantRepository;
    private IAnthologyAdditionalInfoRepository
            anthologyAdditionalInfoRepository;
    private IAuthorAdditionalInfoRepository authorAdditionalInfoRepository;
    private IAuthorRepository authorRepository;
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;

    ArticleService(
            IAnthologyParticipantRepository anthologyParticipantRepository,
            IAnthologyAdditionalInfoRepository
                    anthologyAdditionalInfoRepository,
            IAuthorAdditionalInfoRepository authorAdditionalInfoRepository,
            IAuthorRepository authorRepository,
            IArticleRepository articleRepository,
            IAnthologyRepository anthologyRepository) {
        this.anthologyParticipantRepository = anthologyParticipantRepository;
        this.anthologyAdditionalInfoRepository =
                anthologyAdditionalInfoRepository;
        this.authorAdditionalInfoRepository = authorAdditionalInfoRepository;
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
    }

    @Transactional
    @Override
    public OptionalLong saveArticle(
            CreateArticleDTO createArticleDTO) throws ServiceException {
        try {
            Anthology anthology = this.anthologyRepository.getOne(
                    createArticleDTO.getAnthologyId());
            Author author = this.authorRepository.getOne(
                    createArticleDTO.getAuthorId());
            boolean authorOwnAnthology = false;
            if (anthology.getAuthor().getId().equals(
                    createArticleDTO.getAuthorId())) {
                authorOwnAnthology = true;
            }
            if (!authorOwnAnthology) {
                AnthologyParticipant.PK anthologyParticipantPk = new
                        AnthologyParticipant.PK();
                anthologyParticipantPk.setAnthology(anthology);
                anthologyParticipantPk.setAuthor(author);
                if (!this.anthologyParticipantRepository.existsById(
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
            long anthologyArticleNumber = anthology.getAdditionalInfo()
                    .getArticleNumber();
            anthology.getAdditionalInfo().setArticleNumber(
                    anthologyArticleNumber + 1);
            this.anthologyAdditionalInfoRepository.save(
                    anthology.getAdditionalInfo());
            long authorArticleNumber = author.getAdditionalInfo()
                    .getArticleNumber();
            author.getAdditionalInfo().setArticleNumber(
                    authorArticleNumber + 1);
            this.authorAdditionalInfoRepository.save(
                    author.getAdditionalInfo());
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
                        ICommonConstant.DefaultValue
                                .ARTICLE_SELECTED_TAG_INIT_WEIGHT);
                this.articleTagRepository.save(articleTag);
            });
            return OptionalLong.of(article.getId());
        } catch (Exception e) {
            logger.error("Fail to save article because of exception.", e);
            throw new ServiceException(
                    "Fail to save article because of exception.", e);
        }
    }

    @Override
    public void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO) {
    }

    @Override
    public void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO) {
    }

    @Override
    public void praiseArticle(ArticlePraiseDTO articlePraiseDTO) {
    }

    @Override
    public Optional<ArticleDetailDTO> viewArticle(
            ArticleViewDTO articleViewDTO) {
        return Optional.empty();
    }
}
