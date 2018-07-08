package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IResourceMapper {
    Resource findOneByMd5(@Param("md5") String md5);

    Resource findOneById(@Param("id") long id);

    void create(Resource resource);

    void update(Resource resource);
}
