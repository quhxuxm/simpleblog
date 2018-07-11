package com.quhxuxm.quh.project.simpleblog.service.api;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;

import java.util.Optional;
import java.util.OptionalLong;

public interface IArticleService {
    OptionalLong saveArticle(CreateArticleDTO createArticleDTO) throws ServiceException;

    void assignTagsToArticle(ArticleAssignTagsDTO articleAssignTagsDTO);

    void bookmarkArticle(ArticleBookmarkDTO articleBookmarkDTO);

    void praiseArticle(ArticlePraiseDTO articlePraiseDTO);

    Optional<ArticleDetailDTO> viewArticle(ArticleViewDTO articleViewDTO);
}
