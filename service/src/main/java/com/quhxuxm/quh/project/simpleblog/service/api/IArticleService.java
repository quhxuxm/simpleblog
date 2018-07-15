package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.Article;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<ArticleSummaryDTO> listArticleSummariesOrderByBookmarkNumber(Pageable pageable);

    Page<ArticleSummaryDTO> listArticleSummariesOrderByPraiseNumber(Pageable pageable);

    Page<ArticleSummaryDTO> listArticleSummariesOrderByViewNumber(Pageable pageable);

    Page<ArticleSummaryDTO> listArticleSummariesOrderByCommentNumber(Pageable pageable);

    Page<ArticleSummaryDTO> listArticleSummariesOrderByAuthorInterests(Pageable pageable, Long authorId);

    Page<ArticleSummaryDTO> listArticleSummariesInAnthology(Pageable pageable, Long anthologyId);

    Page<ArticleSummaryDTO> listArticleSummariesFromAuthor(Pageable pageable, Long authorId);
}
