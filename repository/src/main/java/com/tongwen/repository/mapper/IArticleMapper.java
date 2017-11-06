package com.tongwen.repository.mapper;

import com.tongwen.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IArticleMapper {
    List<ArticleSummary> getSummariesOrderByPublishDate(
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> getSummariesOrderByViewNumber(
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> getSummariesOrderByCommentNumber(
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> getSummariesOrderByPraiseNumber(
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> getSummariesOrderByBookmarkNumber(
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    ArticleDetail getArticleDetail(
            @Param("id")
                    long id);

    void create(Article article);

    void update(Article article);

    Article getOne(
            @Param("id")
                    long id);

    ArticleAdditionalInfo getAdditionalInfo(
            @Param("articleId")
                    long articleId);

    List<ArticleAdditionalInfo> getAdditionalInfoList(
            @Param("articleIdList")
                    List<Long> articleIdList);

    void createAdditionalInfo(ArticleAdditionalInfo additionalInfo);

    void updateAdditionalInfo(ArticleAdditionalInfo additionalInfo);
}
