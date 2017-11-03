package com.tongwen.service.impl;

import com.tongwen.repository.mapper.IArticleMapper;
import com.tongwen.service.api.IArticleService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private final IArticleMapper articleMapper;

    public ArticleService(IArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void writeArticle(ArticleEditDetail articleEditDetail)
            throws ServiceException {
        if (articleEditDetail.getAnthologyId() == null) {
            throw new ServiceException(ServiceException.Code.ILLEGAL_STATUS);
        }
        try {
            this.articleMapper.create(articleEditDetail);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }
}
