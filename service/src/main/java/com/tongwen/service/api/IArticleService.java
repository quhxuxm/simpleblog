package com.tongwen.service.api;

import com.tongwen.service.exception.ServiceException;

public interface IArticleService {
    void writeArticle(ArticleEditDetail articleEditDetail)
            throws ServiceException;
}
