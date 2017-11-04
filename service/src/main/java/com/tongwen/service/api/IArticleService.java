package com.tongwen.service.api;

import com.tongwen.domain.Article;
import com.tongwen.service.exception.ServiceException;

public interface IArticleService {
    void create(Article article) throws ServiceException;

    void update(Article article) throws ServiceException;
}
