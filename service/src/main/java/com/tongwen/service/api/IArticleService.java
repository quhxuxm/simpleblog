package com.tongwen.service.api;

import com.tongwen.domain.Article;
import com.tongwen.domain.ArticleSummary;
import com.tongwen.service.exception.ServiceException;

import java.util.List;

public interface IArticleService {
    void create(Article article) throws ServiceException;

    void update(Article article) throws ServiceException;

    List<ArticleSummary> getSummariesOrderByPublishDate(int start, int pageSize, boolean isDesc)
        throws ServiceException;
}
