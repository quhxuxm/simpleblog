package com.tongwen.service.impl;

import com.tongwen.domain.Article;
import com.tongwen.domain.ArticleSummary;
import com.tongwen.repository.mapper.IArticleMapper;
import com.tongwen.service.api.IArticleService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService implements IArticleService {
    private final IArticleMapper articleMapper;

    @Autowired
    public ArticleService(IArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Transactional
    @Override
    public void create(Article article) throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            this.articleMapper.create(article);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public void update(Article article) throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            this.articleMapper.update(article);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleSummary> getSummariesOrderByPublishDate(int start, int pageSize,
        boolean isDesc) throws ServiceException {
        try {
            return this.articleMapper.getSummariesOrderByPublishDate(start, pageSize, true);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
