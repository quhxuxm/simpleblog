package com.tongwen.repository.mapper;

import com.tongwen.domain.ArticleSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleMapper {
    List<ArticleSummary> getArticleSummariesOrderBy(
            @Param("orderByColumnName")
                    String orderByColumnName);
}
