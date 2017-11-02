package com.tongwen.repository.mapper;

import com.tongwen.domain.AnthologyEditDetail;
import com.tongwen.domain.AnthologyReadDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IAnthologyMapper {
    void create(AnthologyEditDetail anthologyEditDetail);

    Map<Long, String> findAnthologyIdAndTitleByAuthorId(@Param("authorId") long authorId);
}
