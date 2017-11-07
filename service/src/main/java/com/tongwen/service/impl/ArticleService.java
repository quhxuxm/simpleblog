package com.tongwen.service.impl;

import com.tongwen.domain.*;
import com.tongwen.repository.mapper.IArticleMapper;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.api.IArticleService;
import com.tongwen.service.api.IImageService;
import com.tongwen.service.exception.ServiceException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService implements IArticleService {
    private static final Logger logger = LoggerFactory
            .getLogger(ArticleService.class);
    private static final String ARTICLE_ACCEPTABLE_TAGS = "b, blockquote, br, div, em, h1, h2, h3, h4, i, img, p, pre, q, strike, strong";
    private static final String IMG_SELECTOR = "img";
    private final IAnthologyService anthologyService;
    private final IImageService imageService;
    private final IArticleMapper articleMapper;
    @Value("${article.summariesCollection.pageSize}")
    private int articleSummariesCollectionPageSize;

    @Autowired
    public ArticleService(IArticleMapper articleMapper,
            IAnthologyService anthologyService, IImageService imageService) {
        this.articleMapper = articleMapper;
        this.anthologyService = anthologyService;
        this.imageService = imageService;
    }

    private Whitelist createArticleContentWhitelist() {
        Whitelist whitelist = new Whitelist();
        whitelist.preserveRelativeLinks(true);
        whitelist.addTags(ARTICLE_ACCEPTABLE_TAGS.split(","));
        whitelist.addAttributes("img", "src");
        return whitelist;
    }

    private void parseAndCleanupArticleContent(Article article,
            String imageBasePath) {
        String cleanedArticleContent = Jsoup.clean(article.getContent(),
                this.createArticleContentWhitelist());
        Document contentDocument = Jsoup.parse(cleanedArticleContent);
        Elements imgElements = contentDocument.select(IMG_SELECTOR);
        Long coverImageId = null;
        for (Element imgElement : imgElements) {
            //Save a new image.
            String src = imgElement.attr("src");
            if (src == null) {
                imgElement.remove();
                logger.warn(
                        "Ignore the image because it fail to store into database.");
                continue;
            }
            if (src.startsWith(imageBasePath)) {
                if (coverImageId != null) {
                    continue;
                }
                try {
                    coverImageId = Long.parseLong(
                            src.substring(imageBasePath.length() + 1));
                } catch (NumberFormatException e) {
                    logger.warn(
                            "Ignore the image because it fail to store into database.");
                }
                continue;
            }
            if (!src.startsWith("data:")) {
                imgElement.remove();
                logger.warn(
                        "Ignore the image because it fail to store into database.");
                continue;
            }
            String[] srcParts = src.split(",");
            if (srcParts.length < 2) {
                imgElement.remove();
                logger.warn(
                        "Ignore the image because it fail to store into database.");
                continue;
            }
            String typePart = srcParts[0];
            if (!typePart.contains(";")) {
                logger.warn(
                        "Fail to parse the image because of src format incorrect.");
                continue;
            }
            int typeEndIndex = typePart.indexOf(";");
            String type = typePart.substring("data:".length(), typeEndIndex);
            String srcBase64Part = srcParts[1];
            try {
                byte[] imageByteArray = Base64.getDecoder()
                        .decode(srcBase64Part);
                String md5 = this.imageService.md5(imageByteArray);
                Image image = this.imageService.loadByMd5(md5);
                if (image == null) {
                    image = new Image();
                    image.setContent(imageByteArray);
                    image.setMd5(md5);
                    image.setType(type);
                    this.imageService.create(image);
                }
                imgElement.attr("src", imageBasePath + "/" + image.getId());
                if (coverImageId != null) {
                    continue;
                }
                coverImageId = image.getId();
            } catch (Exception e) {
                imgElement.remove();
                logger.warn("Fail to parse the image because of exception.", e);
            }
        }
        article.setContent(contentDocument.body().html());
        article.setCoverImageId(coverImageId);
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void create(Article article, Author author, String imageBasePath)
            throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(
                    ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            Anthology targetAnthology = this.anthologyService
                    .getAnthology(article.getAnthologyId());
            if (targetAnthology == null) {
                throw new ServiceException(
                        ServiceException.Code.ANTHOLOGY_NOT_EXIST);
            }
            if (!targetAnthology.getAuthorId().equals(author.getId())) {
                throw new ServiceException(
                        ServiceException.Code.ANTHOLOGY_NOT_BELONG_TO_AUTHOR);
            }
            ArticleAdditionalInfo additionalInfo = new ArticleAdditionalInfo();
            this.articleMapper.createAdditionalInfo(additionalInfo);
            article.setAdditionalInfoId(additionalInfo.getId());
            parseAndCleanupArticleContent(article, imageBasePath);
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

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public void update(Article article, Author author, String imageBasePath)
            throws ServiceException {
        if (article.getAnthologyId() == null) {
            throw new ServiceException(
                    ServiceException.Code.ANTHOLOGY_NOT_ASSIGNED);
        }
        try {
            Anthology targetAnthology = this.anthologyService
                    .getAnthology(article.getAnthologyId());
            if (targetAnthology == null) {
                throw new ServiceException(
                        ServiceException.Code.ANTHOLOGY_NOT_EXIST);
            }
            if (!targetAnthology.getAuthorId().equals(author.getId())) {
                throw new ServiceException(
                        ServiceException.Code.ANTHOLOGY_NOT_BELONG_TO_AUTHOR);
            }
            article.setUpdateDate(new Date());
            parseAndCleanupArticleContent(article, imageBasePath);
            this.articleMapper.update(article);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public ArticleDetail viewDetail(long id) throws ServiceException {
        try {
            ArticleAdditionalInfo additionalInfo = this.articleMapper
                    .getAdditionalInfo(id);
            additionalInfo.setViewNumber(additionalInfo.getViewNumber() + 1);
            this.articleMapper.updateAdditionalInfo(additionalInfo);
            return this.articleMapper.getArticleDetail(id);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleSummary> getSummariesOrderByPublishDate(int start,
            boolean isDesc) throws ServiceException {
        try {
            return this.articleMapper.getSummariesOrderByPublishDate(start,
                    this.articleSummariesCollectionPageSize, true);
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleAdditionalInfo getAdditionalInfo(long articleId)
            throws ServiceException {
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
            List<Long> articleIdList = articleSummaries.stream()
                    .map(ArticleSummary::getId).collect(Collectors.toList());
            List<ArticleAdditionalInfo> articleAdditionalInfos = this.articleMapper
                    .getAdditionalInfoList(articleIdList);
            Map<Long, ArticleAdditionalInfo> articleAdditionalInfoMap = new HashMap<>();
            for (ArticleAdditionalInfo info : articleAdditionalInfos) {
                articleAdditionalInfoMap.put(info.getId(), info);
            }
            Map<Long, ArticleAdditionalInfo> result = new HashMap<>();
            for (ArticleSummary articleSummary : articleSummaries) {
                result.put(articleSummary.getId(), articleAdditionalInfoMap
                        .get(articleSummary.getAdditionalInfoId()));
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException(e, ServiceException.Code.SYSTEM_ERROR);
        }
    }

    @Override
    public String extractArticleContentPlainText(String content) {
        String cleanedContextHtml = Jsoup
                .clean(content, this.createArticleContentWhitelist());
        Document contentDocument = Jsoup.parse(cleanedContextHtml);
        return contentDocument.text();
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public long praiseArticle(long id) throws ServiceException {
        return 0;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public long bookmarkArticle(long id) throws ServiceException {
        return 0;
    }
}
