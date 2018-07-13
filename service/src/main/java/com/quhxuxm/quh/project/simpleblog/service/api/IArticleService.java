package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

import java.util.Optional;
import java.util.OptionalLong;

public interface IArticleService {
    OptionalLong saveArticle(CreateArticleDTO createArticleDTO)
            throws ServiceException;

    void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO)
            throws ServiceException;

    void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO)
            throws ServiceException;

    void praiseArticle(ArticlePraiseDTO articlePraiseDTO)
            throws ServiceException;

    Optional<ArticleDetailDTO> viewArticle(ArticleViewDTO articleViewDTO)
            throws ServiceException;

    void increaseAuthorTagWeightAccordingToArticleTags(Author author,
            Article article) throws ServiceException;
}
