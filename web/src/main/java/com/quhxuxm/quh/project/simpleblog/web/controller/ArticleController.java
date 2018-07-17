package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.ArticleSummaryDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private enum ArticleListCategory {
        ORDER_BY_LAST_UPDATE,
        ORDER_BY_BOOKMARK_NUMBER,
        ORDER_BY_VIEW_NUMBER
    }

    private IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/list")
    @JsonView(WebApiResult.class)
    public WebApiResult list(
            @RequestParam(required = false)
                    ArticleListCategory category,
            @RequestParam(required = false)
                    Integer pageIndex,
            @RequestParam(required = false)
                    Integer pageSize, boolean isAsc) {
        try {
            if (pageIndex == null) {
                pageIndex = 0;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
            if (category == null) {
                category = ArticleListCategory.ORDER_BY_LAST_UPDATE;
            }
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ArticleSummaryDTO> page;
            switch (category) {
            case ORDER_BY_LAST_UPDATE:
                page = this.articleService
                        .listArticleSummariesOrderByCreateDate(pageable, isAsc);
            case ORDER_BY_VIEW_NUMBER:
                page = this.articleService
                        .listArticleSummariesOrderByViewNumber(pageable, isAsc);
            default:
                page = this.articleService
                        .listArticleSummariesOrderByCreateDate(pageable, isAsc);
            }
            WebApiResult result = new WebApiResult();
            result.setStatus(WebApiResult.Status.SUCCESS);
            result.setPayload(page);
            return result;
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }
}
