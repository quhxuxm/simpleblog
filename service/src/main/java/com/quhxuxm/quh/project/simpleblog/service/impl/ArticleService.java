package com.quhxuxm.quh.project.simpleblog.service.impl;
import com.quhxuxm.quh.project.simpleblog.domain.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.repository.IAnthologyRepository;
import com.quhxuxm.quh.project.simpleblog.repository.IArticleRepository;
import com.quhxuxm.quh.project.simpleblog.repository.IAuthorRepository;
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

class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory.getLogger(
            ArticleService.class);
    private IAuthorRepository authorRepository;
    private IArticleRepository articleRepository;
    private IAnthologyRepository anthologyRepository;

    ArticleService(IAuthorRepository authorRepository, IArticleRepository articleRepository,
                   IAnthologyRepository anthologyRepository) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
        this.anthologyRepository = anthologyRepository;
    }

    @Transactional
    @Override
    public OptionalLong saveArticle(
            CreateArticleDTO createArticleDTO) throws ServiceException {
        try {
            Article article = new Article();
            article.setTitle(createArticleDTO.getTitle());
            article.setContent(createArticleDTO.getContent());
            article.setCreateDate(new Date());
            article.setSummary(createArticleDTO.getSummary());
            Anthology anthology = this.anthologyRepository.getOne(
                    createArticleDTO.getAnthologyId());
            article.setAnthology(anthology);
            articleRepository.save(article);
            Author author = this.authorRepository.getOne(createArticleDTO.getAuthorId());
            long authorArticleNumber= author.getAdditionalInfo().getArticleNumber();
            author.getAdditionalInfo().setArticleNumber(authorArticleNumber+1);
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
