package com.tongwen.service.impl;

import com.tongwen.domain.*;
import com.tongwen.repository.mapper.IArticleMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.api.IArticleService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleService implements IArticleService {
    private final IAnthologyService anthologyService;
    private final IArticleMapper articleMapper;
    @Value("${article.summariesCollection.pageSize}")
    private int articleSummariesCollectionPageSize;

    @Autowired
    public ArticleService(IArticleMapper articleMapper, IAnthologyService anthologyService) {
        this.articleMapper = articleMapper;
        this.anthologyService = anthologyService;
    }

    @Transactional
    @Override
    public void create(Article article, Author author) throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            Anthology targetAnthology =
                this.anthologyService.getAnthology(article.getAnthologyId());
            if (targetAnthology == null) {
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_EXIST);
            }
            if (!targetAnthology.getAuthorId().equals(author.getId())) {
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_BELONG_TO_AUTHOR);
            }
            ArticleAdditionalInfo additionalInfo = new ArticleAdditionalInfo();
            this.articleMapper.createAdditionalInfo(additionalInfo);
            article.setAdditionalInfoId(additionalInfo.getId());
            this.articleMapper.create(article);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public Article get(long id) throws ServiceException {
        try {
            return this.articleMapper.getOne(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public void update(Article article, Author author) throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            Anthology targetAnthology =
                this.anthologyService.getAnthology(article.getAnthologyId());
            if (targetAnthology == null) {
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_EXIST);
            }
            if (!targetAnthology.getAuthorId().equals(author.getId())) {
                throw new ServiceException(ServiceException.Code.ANTHOLOGY_NOT_BELONG_TO_AUTHOR);
            }
            this.articleMapper.update(article);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional
    @Override
    public ArticleDetail viewDetail(long id) throws ServiceException {
        try {
            ArticleAdditionalInfo additionalInfo = this.articleMapper.getAdditionalInfo(id);
            additionalInfo.setViewNumber(additionalInfo.getViewNumber() + 1);
            this.articleMapper.updateAdditionalInfo(additionalInfo);
            return this.articleMapper.getArticleDetail(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleSummary> getSummariesOrderByPublishDate(int start, boolean isDesc)
        throws ServiceException {
        try {
            return this.articleMapper
                .getSummariesOrderByPublishDate(start, this.articleSummariesCollectionPageSize,
                    true);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleAdditionalInfo getAdditionalInfo(long articleId) throws ServiceException {
        try {
            return this.articleMapper.getAdditionalInfo(articleId);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Long, ArticleAdditionalInfo> getAdditionalInfoList(
        List<ArticleSummary> articleSummaries) throws ServiceException {
        if (articleSummaries == null || articleSummaries.isEmpty()) {
            return new HashMap<>();
        }
        try {
            List<Long> articleIdList =
                articleSummaries.stream().map(ArticleSummary::getId).collect(Collectors.toList());
            List<ArticleAdditionalInfo> articleAdditionalInfos =
                this.articleMapper.getAdditionalInfoList(articleIdList);
            Map<Long, ArticleAdditionalInfo> articleAdditionalInfoMap = new HashMap<>();
            for (ArticleAdditionalInfo info : articleAdditionalInfos) {
                articleAdditionalInfoMap.put(info.getId(), info);
            }
            Map<Long, ArticleAdditionalInfo> result = new HashMap<>();
            for (ArticleSummary articleSummary : articleSummaries) {
                result.put(articleSummary.getId(),
                    articleAdditionalInfoMap.get(articleSummary.getAdditionalInfoId()));
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public String extractArticleContentPlainText(String content) {
        return null;
    }

    @Override
    public long praiseArticle(long id) throws ServiceException {
        return 0;
    }

    @Override
    public long bookmarkArticle(long id) throws ServiceException {
        return 0;
    }
}
