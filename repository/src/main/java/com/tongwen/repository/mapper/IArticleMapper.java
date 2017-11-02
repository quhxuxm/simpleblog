package com.tongwen.repository.mapper;

import com.tongwen.domain.ArticleEditDetail;
import com.tongwen.domain.ArticleReadSummary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleMapper {
    List<ArticleReadSummary> getArticleReadSummariesOrderByPublishDateDesc(int start, int pageSize);

    void create(ArticleEditDetail articleEditDetail);

    void update(ArticleEditDetail articleEditDetail);
}
