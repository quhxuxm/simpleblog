package com.quhxuxm.quh.project.simpleblog.repository.view;

import com.quhxuxm.quh.project.simpleblog.domain.view.ArticleDetail;
import com.quhxuxm.quh.project.simpleblog.domain.view.ArticleSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IArticleViewMapper {
    List<ArticleSummary> findSummariesByTagsOrderByPublishDate(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByTagsOrderByViewNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByTagsOrderByCommentNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByTagsOrderByPraiseNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByTagsOrderByBookmarkNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByTagsOrderByArticleNumber(
            @Param("start")
                    int start,
            @Param("tags")
                    Set<String> tags,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByPublishDate(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByViewNumber(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByCommentNumber(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByPraiseNumber(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByBookmarkNumber(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    List<ArticleSummary> findSummariesByAnthologyAndTagsOrderByArticleNumber(
            @Param("anthologyId")
                    long anthologyId,
            @Param("tags")
                    Set<String> tags,
            @Param("start")
                    int start,
            @Param("pageSize")
                    int pageSize,
            @Param("isDesc")
                    boolean isDesc);

    ArticleDetail findOneDetailById(
            @Param("id")
                    long id);
}
