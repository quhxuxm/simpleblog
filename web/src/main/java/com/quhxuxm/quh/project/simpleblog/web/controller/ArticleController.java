package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.tongwen.domain.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.web.request.ArticleEditRequest;
import com.quhxuxm.quh.project.simpleblog.web.response.ArticleBookmarkResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.ArticleEditResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.ArticlePraiseResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.common.CardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final IAnthologyService anthologyService;
    private final IAuthorService authorService;
    private final IArticleService articleService;
    private final ServletContext servletContext;
    @Value("${article.title.maxLength}")
    private int titleMaxLength;
    @Value("${article.title.minLength}")
    private int titleMinLength;
    @Value("${article.content.maxLength}")
    private int contentMaxLength;
    @Value("${article.content.minLength}")
    private int contentMinLength;

    @Autowired
    public ArticleController(IAnthologyService anthologyService, IArticleService articleService,
        IAuthorService authorService, ServletContext servletContext) {
        this.anthologyService = anthologyService;
        this.articleService = articleService;
        this.authorService = authorService;
        this.servletContext = servletContext;
    }

    @GetMapping("/{articleId}/view")
    public ModelAndView view(@PathVariable("articleId") Long articleId) throws Exception {
        ModelAndView result = new ModelAndView("article");
        ArticleDetail articleDetail = this.articleService.viewDetail(articleId);
        result.addObject("article", articleDetail);
        ArticleAdditionalInfo additionalInfo = this.articleService.getAdditionalInfo(articleId);
        result.addObject("articleAdditionalInfo", additionalInfo);
        AuthorAdditionalInfo authorAdditionalInfo =
            this.authorService.getAdditionalInfo(articleDetail.getAuthorId());
        result.addObject("authorAdditionalInfo", authorAdditionalInfo);
        return result;
    }

    @GetMapping(value = "/{articleId}/praise", produces = "application/json")
    @ResponseBody
    public ArticlePraiseResponse praise(@PathVariable("articleId") Long articleId) {
        ArticlePraiseResponse praiseResponse = new ArticlePraiseResponse();
        Long praiseNumber = null;
        try {
            praiseNumber = this.articleService.praiseArticle(articleId);
        } catch (ServiceException e) {
            logger.error("Fail to praise article because of exception.", e);
            praiseResponse.setSuccess(false);
            return praiseResponse;
        }
        praiseResponse.setSuccess(true);
        praiseResponse.setPraiseNumber(praiseNumber);
        return praiseResponse;
    }

    @GetMapping(value = "/{articleId}/bookmark", produces = "application/json")
    @ResponseBody
    public ArticleBookmarkResponse bookmark(@PathVariable("articleId") Long articleId) {
        ArticleBookmarkResponse bookmarkResponse = new ArticleBookmarkResponse();
        Long bookmarkNumber = null;
        try {
            bookmarkNumber = this.articleService.bookmarkArticle(articleId);
        } catch (ServiceException e) {
            bookmarkResponse.setSuccess(false);
            return bookmarkResponse;
        }
        bookmarkResponse.setSuccess(true);
        bookmarkResponse.setBookmarkNumber(bookmarkNumber);
        return bookmarkResponse;
    }

    @GetMapping("/write")
    public ModelAndView showWrite(HttpSession session) throws Exception {
        ModelAndView result = new ModelAndView("article-editor");
        Author author =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        List<AnthologySummary> anthologies =
            this.anthologyService.getAnthologySummaries(author.getId(), 0, false);
        result.addObject("defaultAnthologyId", author.getDefaultAnthologyId());
        result.addObject("anthologies", anthologies);
        return result;
    }

    @PostMapping(value = "/write", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public ArticleEditResponse write(@RequestBody ArticleEditRequest articleEditRequest,
        HttpSession httpSession) {
        ArticleEditResponse response = new ArticleEditResponse();
        this.validateArticleEditRequest(response, articleEditRequest);
        if (!response.isSuccess()) {
            return response;
        }
        Article article = new Article();
        article.setContent(articleEditRequest.getContent());
        article.setSummary(articleEditRequest.getSummary());
        article.setTitle(articleEditRequest.getTitle());
        article.setAnthologyId(articleEditRequest.getAnthologyId());
        article.setPublished(articleEditRequest.isPublish());
        article.setPublishDate(new Date());
        Author authorInSession =
            (Author) httpSession.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        try {
            String imageBasePath = this.servletContext.getContextPath() + "/dimage";
            this.articleService.create(article, authorInSession, imageBasePath);
        } catch (Exception e) {
            response.setSuccess(false);
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.SYSTEM_ERROR);
            logger.error("Fail to create article because of exception.", e);
            return response;
        }
        response.setArticleId(article.getId());
        response.setSuccess(true);
        return response;
    }

    @GetMapping({"/{articleId}/update"})
    public ModelAndView showArticleEdit(@PathVariable(name = "articleId") Long articleId,
        HttpSession session) throws Exception {
        ModelAndView result = new ModelAndView("article-editor");
        Author authorInSession =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        result.addObject("article", this.articleService.get(articleId));
        List<AnthologySummary> anthologies =
            this.anthologyService.getAnthologySummaries(authorInSession.getId(), 0, false);
        result.addObject("defaultAnthologyId", authorInSession.getDefaultAnthologyId());
        result.addObject("anthologies", anthologies);
        return result;
    }

    @PostMapping(value = "/{articleId}/update", consumes = {"application/json"},
        produces = {"application/json"})
    @ResponseBody
    public ArticleEditResponse update(@PathVariable(name = "articleId") Long articleId,
        @RequestBody ArticleEditRequest articleEditRequest, HttpSession httpSession) {
        ArticleEditResponse response = new ArticleEditResponse();
        this.validateArticleEditRequest(response, articleEditRequest);
        if (!response.isSuccess()) {
            return response;
        }
        try {
            Article article = this.articleService.get(articleId);
            article.setContent(articleEditRequest.getContent());
            article.setSummary(articleEditRequest.getSummary());
            article.setTitle(articleEditRequest.getTitle());
            article.setAnthologyId(articleEditRequest.getAnthologyId());
            article.setPublished(articleEditRequest.isPublish());
            article.setPublishDate(new Date());
            response.setArticleId(articleId);
            Author authorInSession = (Author) httpSession
                .getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
            String imageBasePath = this.servletContext.getContextPath() + "/dimage";
            this.articleService.update(article, authorInSession, imageBasePath);
        } catch (Exception e) {
            response.setSuccess(false);
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.SYSTEM_ERROR);
            logger.error("Fail to update article because of exception.", e);
            return response;
        }
        response.setSuccess(true);
        return response;
    }

    private void validateArticleEditRequest(ArticleEditResponse response,
        ArticleEditRequest articleEditRequest) {
        if (articleEditRequest.getTitle() == null
            || articleEditRequest.getTitle().trim().length() == 0) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.TITLE_IS_EMPTY);
        }
        if (articleEditRequest.getTitle().trim().length() > this.titleMaxLength) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.TITLE_TOO_LONG);
        }
        if (articleEditRequest.getTitle().trim().length() < this.titleMinLength) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.TITLE_TOO_SHORT);
        }
        String contentPlainText = null;
        try {
            contentPlainText =
                this.articleService.extractArticleContentPlainText(articleEditRequest.getContent());
        } catch (ServiceException e) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.SYSTEM_ERROR);
        }
        if (contentPlainText == null || contentPlainText.trim().length() == 0) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.CONTENT_IS_EMPTY);
        }
        if (contentPlainText.trim().length() > this.contentMaxLength) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.CONTENT_TOO_LONG);
        }
        if (contentPlainText.trim().length() < this.contentMinLength) {
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.CONTENT_TOO_SHORT);
        }
        if (!response.getErrorCodes().isEmpty()) {
            response.setSuccess(false);
        }
    }

    @GetMapping("/allPublishedArticleSummariesCollection")
    public ModelAndView allPublishedArticleSummariesCollection(
        @RequestParam(name = "start", defaultValue = "0", required = false) int start,
        @RequestParam(name = "desc", defaultValue = "true", required = false) boolean isDesc)
        throws Exception {
        List<ArticleSummary> articleSummariesCollection =
            this.articleService.getSummariesOrderByPublishDate(start, isDesc);
        return this
            .preparedArticleSummariesCollectionToModelAndView(articleSummariesCollection, start);
    }

    @GetMapping("/anthologyArticleSummariesCollection/{anthologyId}")
    public ModelAndView anthologyArticleSummariesCollection(
        @PathVariable(name = "anthologyId", required = true) long anthologyId,
        @RequestParam(name = "start", defaultValue = "0", required = false) int start,
        @RequestParam(name = "desc", defaultValue = "true", required = false) boolean isDesc,
        HttpSession session) throws Exception {
        Author authorInSession =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        Anthology anthology = this.anthologyService.getAnthology(anthologyId);
        if (anthology == null) {
            return this.preparedArticleSummariesCollectionToModelAndView(new ArrayList<>(), start);
        }
        if (authorInSession != null && (anthology.getAuthorId().equals(authorInSession.getId()))) {
            List<ArticleSummary> articleSummariesCollection =
                this.articleService.getAllArticleSummariesInAnthology(anthologyId, start, isDesc);
            return this.preparedArticleSummariesCollectionToModelAndView(articleSummariesCollection,
                start);
        }
        List<ArticleSummary> articleSummariesCollection =
            this.articleService.getPublishedArticleSummariesInAnthology(anthologyId, start, isDesc);
        return this
            .preparedArticleSummariesCollectionToModelAndView(articleSummariesCollection, start);
    }

    private ModelAndView preparedArticleSummariesCollectionToModelAndView(
        List<ArticleSummary> articleSummariesCollection, int start) throws ServiceException {
        ModelAndView result = new ModelAndView("/fragment/article/summariesCollection");
        int summariesCollectionSize = 0;
        Map<Long, ArticleAdditionalInfo> additionalInfoMap =
            this.articleService.getAdditionalInfoList(articleSummariesCollection);
        List<CardInfo> cardInfoList = articleSummariesCollection.stream().map(articleSummary -> {
            CardInfo cardInfo = new CardInfo();
            cardInfo.setId(articleSummary.getId());
            cardInfo.setAuthorId(articleSummary.getAuthorId());
            cardInfo.setAuthorNickName(articleSummary.getAuthorNickName());
            cardInfo.setTitle(articleSummary.getTitle());
            cardInfo.setSummary(articleSummary.getSummary());
            cardInfo.setPublishDate(articleSummary.getPublishDate());
            cardInfo.setCoverImageId(articleSummary.getCoverImageId());
            cardInfo.setAuthorIconImageId(articleSummary.getAuthorIconImageId());
            return cardInfo;
        }).collect(Collectors.toList());
        result.addObject("summariesCollection", cardInfoList);
        result.addObject("additionalInfoMap", additionalInfoMap);
        summariesCollectionSize = articleSummariesCollection.size();
        int nextStart = start + summariesCollectionSize;
        result.addObject("nextStart", nextStart);
        return result;
    }
}
