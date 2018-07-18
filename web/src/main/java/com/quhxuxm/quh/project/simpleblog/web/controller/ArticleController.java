package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.ArticleDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.ArticleSummaryDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.ArticleViewDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public WebApiResult<Page<ArticleSummaryDTO>> list(
            @RequestParam(required = false, name = "cindex")
                    Integer categoryIndex,
            @RequestParam(
                    name = ICommonConstant.RequestParameterName.PAGE_INDEX_REQUEST_PARAM_NAME,
                    required = false)
                    Integer pageIndex,
            @RequestParam(
                    name = ICommonConstant.RequestParameterName.PAGE_SIZE_REQUEST_PARAM_NAME,
                    required = false)
                    Integer pageSize,
            @RequestParam(
                    name = ICommonConstant.RequestParameterName.PAGE_IS_ASC,
                    required = false)
                    boolean isAsc) {
        try {
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
                if (categoryIndex >= ArticleListCategory.values().length || categoryIndex < 0) {
                    throw new WebApiException();
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
                    page = this.articleService.listArticleSummariesOrderByBookmarkNumber(pageable, isAsc);
                    break;
                case ORDER_BY_PRAISE_NUMBER:
                    page = this.articleService.listArticleSummariesOrderByPraiseNumber(pageable, isAsc);
                    break;
                case ORDER_BY_COMMENT_NUMBER:
                    page = this.articleService.listArticleSummariesOrderByCommentNumber(pageable, isAsc);
                    break;
                default:
                    page = this.articleService
                            .listArticleSummariesOrderByCreateDate(pageable, isAsc);
            }
            WebApiResult<Page<ArticleSummaryDTO>> result = new WebApiResult<>();
            result.setPayload(page);
            return result;
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }

    @GetMapping(value = "/detail",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebApiResult<ArticleDetailDTO> detail(
            @RequestParam(name = "articleid") Long articleId) {
        try {
            ArticleViewDTO articleViewDTO = new ArticleViewDTO();
            articleViewDTO.setArticleId(articleId);
            Optional<ArticleDetailDTO> articleDetailDTO = this.articleService.viewArticle(articleViewDTO);
            if (articleDetailDTO.isPresent()) {
                WebApiResult<ArticleDetailDTO> result = new WebApiResult<>();
                result.setPayload(articleDetailDTO.get());
                return result;
            }
            throw new WebApiException();
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }
}
