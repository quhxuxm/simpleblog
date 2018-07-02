package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.tongwen.domain.*;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IArticleService {
    void create(Article article, Author author, String imageBasePath)
            throws ServiceException;

    Article get(long id) throws ServiceException;

    void update(Article article, Author author, String imageBasePath)
            throws ServiceException;

    ArticleDetail viewDetail(long id) throws ServiceException;

    long praiseArticle(long id) throws ServiceException;

    long bookmarkArticle(long id) throws ServiceException;

    List<ArticleSummary> getSummariesOrderByPublishDate(int start,
                                                        boolean isDesc) throws ServiceException;

    List<ArticleSummary> getPublishedArticleSummariesInAnthology(
            long anthologyId, int start, boolean isDesc)
            throws ServiceException;

    List<ArticleSummary> getAllArticleSummariesInAnthology(long anthologyId,
            int start, boolean isDesc) throws ServiceException;

    ArticleAdditionalInfo getAdditionalInfo(long articleId)
            throws ServiceException;

    Map<Long, ArticleAdditionalInfo> getAdditionalInfoList(
            List<ArticleSummary> articleSummaries) throws ServiceException;

    String extractArticleContentPlainText(String content)
            throws ServiceException;
}
