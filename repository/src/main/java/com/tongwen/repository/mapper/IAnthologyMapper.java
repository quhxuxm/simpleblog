package com.tongwen.repository.mapper;

import com.tongwen.domain.Anthology;
import com.tongwen.domain.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnthologyMapper {
    void create(Anthology anthology);
}
