package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import com.quhxuxm.quh.project.simpleblog.web.exception.ApiException;
import com.quhxuxm.quh.project.simpleblog.web.request.ApiRequest;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.FailPayload;
import com.quhxuxm.quh.project.simpleblog.web.security.AuthenticatedAuthorDetailHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private enum ArticleListCategory {
        ORDER_BY_LAST_UPDATE,
        ORDER_BY_BOOKMARK_NUMBER,
        ORDER_BY_VIEW_NUMBER,
        ORDER_BY_PRAISE_NUMBER,
        ORDER_BY_COMMENT_NUMBER
    }

    private IArticleService articleService;
    @Value("${simpleblog.article.list.default.pageSize}")
    private int defaultArticleListPageSize;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/list",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> list(
            @RequestParam(required = false, name = "category")
                    Integer categoryIndex,
            @RequestParam(name = "pageindex", required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = this.defaultArticleListPageSize;
        }
        ArticleListCategory category;
        if (categoryIndex == null) {
            category = ArticleListCategory.ORDER_BY_LAST_UPDATE;
        } else {
            if (categoryIndex >= ArticleListCategory.values().length
                    || categoryIndex < 0) {
                FailPayload failPayload = new FailPayload(
                        FailPayload.Type.INPUT_ERROR_WRONG_ARTICLE_LIST_CATEGORY);
                throw new ApiException(failPayload);
            }
            category = ArticleListCategory.values()[categoryIndex];
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<ArticleSummaryDTO> page;
        switch (category) {
            case ORDER_BY_LAST_UPDATE:
                page = this.articleService
                        .listArticleSummariesOrderByCreateDate(pageable, isAsc);
                break;
            case ORDER_BY_VIEW_NUMBER:
                page = this.articleService
                        .listArticleSummariesOrderByViewNumber(pageable, isAsc);
                break;
            case ORDER_BY_BOOKMARK_NUMBER:
                page = this.articleService
                        .listArticleSummariesOrderByBookmarkNumber(pageable,
                                isAsc);
                break;
            case ORDER_BY_PRAISE_NUMBER:
                page = this.articleService
                        .listArticleSummariesOrderByPraiseNumber(pageable,
                                isAsc);
                break;
            case ORDER_BY_COMMENT_NUMBER:
                page = this.articleService
                        .listArticleSummariesOrderByCommentNumber(pageable,
                                isAsc);
                break;
            default:
                page = this.articleService
                        .listArticleSummariesOrderByCreateDate(pageable, isAsc);
        }
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/listinterest",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<Page<ArticleSummaryDTO>> listInterest(
            @RequestParam(name = "pageindex", required = false)
                    Integer pageIndex,
            @RequestParam(name = "pagesize", required = false)
                    Integer pageSize,
            @RequestParam(name = "asc", required = false)
                    boolean isAsc) throws ServiceException {
        if (pageIndex == null) {
            pageIndex = 0;
        }
        if (pageSize == null) {
            pageSize = this.defaultArticleListPageSize;
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        AuthorDetailDTO authenticatedAuthorDetail = AuthenticatedAuthorDetailHolder.INSTANCE
                .get();
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByAuthorInterests(pageable,
                        authenticatedAuthorDetail.getAuthorId(), 5, isAsc);
        ApiResponse<Page<ArticleSummaryDTO>> result = new ApiResponse<>();
        result.setPayload(page);
        return result;
    }

    @GetMapping(value = "/detail/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponse<ArticleDetailDTO> detail(
            @PathVariable(name = "id")
                    Long id) throws ServiceException {
        AuthorDetailDTO authenticatedAuthorDetail = AuthenticatedAuthorDetailHolder.INSTANCE
                .get();
        ArticleViewDTO articleViewDTO = new ArticleViewDTO();
        articleViewDTO.setArticleId(id);
        if (authenticatedAuthorDetail != null) {
            articleViewDTO.setAuthorId(authenticatedAuthorDetail.getAuthorId());
        }
        ArticleDetailDTO articleDetailDTO = this.articleService
                .viewArticle(articleViewDTO);
        if (articleDetailDTO == null) {
            FailPayload articleDetailFailPayload = new FailPayload(
                    FailPayload.Type.ARTICLE_NOT_EXIST_ERROR);
            throw new ApiException(articleDetailFailPayload);
        }
        ApiResponse<ArticleDetailDTO> result = new ApiResponse<>();
        result.setPayload(articleDetailDTO);
        return result;
    }

    @PostMapping(value = "/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<Long> create(
            ApiRequest<CreateArticleDTO> request) throws ServiceException {
        Long articleId = this.articleService.saveArticle(request.getPayload());
        ApiResponse<Long> result = new ApiResponse<>();
        result.setPayload(articleId);
        return result;
    }
}
