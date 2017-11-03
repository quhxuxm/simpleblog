package com.tongwen.repository.mapper;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.AnthologyReadDetail;
import com.tongwen.domain.AnthologySummary;
import com.tongwen.domain.ArticleSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IAnthologyMapper {

    List<AnthologySummary> getSummariesOrderByPublishDate(int start, int pageSize,
            boolean isDesc);

    void create(Anthology anthology);

    Anthology getOne(long id);
}
