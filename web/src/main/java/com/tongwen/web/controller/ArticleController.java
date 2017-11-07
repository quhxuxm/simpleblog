package com.tongwen.web.controller;

import com.tongwen.common.IConstant;
import com.tongwen.domain.*;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.api.IArticleService;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import com.tongwen.web.request.ArticleEditRequest;
import com.tongwen.web.response.ArticleBookmarkResponse;
import com.tongwen.web.response.ArticleEditResponse;
import com.tongwen.web.response.ArticlePraiseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    public ModelAndView view(@PathVariable("articleId") Long articleId) {
        ModelAndView result = new ModelAndView("article");
        try {
            ArticleDetail articleDetail = this.articleService.viewDetail(articleId);
            result.addObject("article", articleDetail);
            ArticleAdditionalInfo additionalInfo = this.articleService.getAdditionalInfo(articleId);
            result.addObject("articleAdditionalInfo", additionalInfo);
            AuthorAdditionalInfo authorAdditionalInfo =
                this.authorService.getAdditionalInfo(articleDetail.getAuthorId());
            result.addObject("authorAdditionalInfo", authorAdditionalInfo);
        } catch (ServiceException e) {
            logger.error("Fail to retrieve article for view because of exception.", e);
        }
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
    public ModelAndView showWrite(HttpSession session) {
        ModelAndView result = new ModelAndView("article-editor");
        Author author =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        try {
            List<AnthologySummary> anthologies =
                this.anthologyService.getAnthologySummaries(author.getId());
            result.addObject("defaultAnthologyId", author.getDefaultAnthologyId());
            result.addObject("anthologies", anthologies);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
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
        Author authorInSession =
            (Author) httpSession.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        try {
            String imageBasePath = this.servletContext.getContextPath() + "/dimage";
            this.articleService.create(article, authorInSession, imageBasePath);
        } catch (Exception e) {
            response.setSuccess(false);
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.SYSTEM_ERROR);
            return response;
        }
        response.setArticleId(article.getId());
        response.setSuccess(true);
        return response;
    }

    @GetMapping({"/{articleId}/update"})
    public ModelAndView showArticleEdit(@PathVariable(name = "articleId") Long articleId,
        HttpSession session) {
        ModelAndView result = new ModelAndView("article-editor");
        try {
            Author authorInSession =
                (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
            result.addObject("article", this.articleService.get(articleId));
            List<AnthologySummary> anthologies =
                this.anthologyService.getAnthologySummaries(authorInSession.getId());
            result.addObject("defaultAnthologyId", authorInSession.getDefaultAnthologyId());
            result.addObject("anthologies", anthologies);
        } catch (ServiceException e) {
            logger.error("Fail to retrieve article for update because of exception.", e);
        }
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
        Article article = new Article();
        article.setContent(articleEditRequest.getContent());
        article.setSummary(articleEditRequest.getSummary());
        article.setTitle(articleEditRequest.getTitle());
        article.setId(articleId);
        article.setAnthologyId(articleEditRequest.getAnthologyId());
        response.setArticleId(articleId);
        Author authorInSession =
            (Author) httpSession.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        try {
            String imageBasePath = this.servletContext.getContextPath() + "/dimage";
            this.articleService.update(article, authorInSession, imageBasePath);
        } catch (Exception e) {
            response.setSuccess(false);
            response.getErrorCodes().add(ArticleEditResponse.ErrorCode.SYSTEM_ERROR);
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

    @GetMapping("/summariesCollection")
    public ModelAndView summariesCollection(
        @RequestParam(name = "start", defaultValue = "0", required = false) int start,
        @RequestParam(name = "desc", defaultValue = "true", required = false) boolean isDesc) {
        ModelAndView result = new ModelAndView("/fragment/article/summariesCollection");
        int summariesCollectionSize = 0;
        try {
            List<ArticleSummary> articleSummariesCollection =
                this.articleService.getSummariesOrderByPublishDate(start, isDesc);
            Map<Long, ArticleAdditionalInfo> additionalInfoMap =
                this.articleService.getAdditionalInfoList(articleSummariesCollection);
            result.addObject("summariesCollection", articleSummariesCollection);
            result.addObject("additionalInfoMap", additionalInfoMap);
            summariesCollectionSize = articleSummariesCollection.size();
        } catch (ServiceException e) {
            logger.error("Fail to get article summaries collection because of exception.", e);
        }
        int nextStart = start + summariesCollectionSize;
        result.addObject("nextStart", nextStart);
        return result;
    }
}
