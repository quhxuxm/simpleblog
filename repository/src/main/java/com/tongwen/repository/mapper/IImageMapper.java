package com.tongwen.repository.mapper;

import com.tongwen.domain.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageMapper {
    Image findByMd5(@Param("md5") String md5);

    Image findById(@Param("id") long id);

    void create(Image image);
}
