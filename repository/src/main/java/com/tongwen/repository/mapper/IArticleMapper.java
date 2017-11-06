package com.tongwen.repository.mapper;

import com.tongwen.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    ArticleDetail getArticleDetail(long id);

    void create(Article article);

    void update(Article article);

    Article getOne(
            @Param("id")
                    long id);

    ArticleAdditionalInfo getAdditionalInfo(
            @Param("articleId")
                    long articleId);

    void createAdditionalInfo(ArticleAdditionalInfo additionalInfo);

    void updateAdditionalInfo(ArticleAdditionalInfo additionalInfo);
}
