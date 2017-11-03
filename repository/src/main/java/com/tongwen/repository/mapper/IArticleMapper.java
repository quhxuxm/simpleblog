package com.tongwen.repository.mapper;

import com.tongwen.domain.Article;
import com.tongwen.domain.ArticleSummary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleMapper {
    List<ArticleSummary> getSummariesOrderByPublishDate(int start, int pageSize,
            boolean isDesc);

    List<ArticleSummary> getSummariesOrderByViewNumber(int start, int pageSize,
            boolean isDesc);

    List<ArticleSummary> getSummariesOrderByCommentNumber(int start,
            int pageSize, boolean isDesc);

    List<ArticleSummary> getSummariesOrderByPraiseNumber(int start,
            int pageSize, boolean isDesc);

    List<ArticleSummary> getSummariesOrderByBookmarkNumber(int start,
            int pageSize, boolean isDesc);

    void create(Article article);

    void update(Article article);

    Article getOne(long id);
}
