package com.quhxuxm.quh.project.simpleblog.repository.view;

import org.apache.ibatis.annotations.Param;

import com.quhxuxm.quh.project.simpleblog.domain.view.AuthorDetail;

public interface IAuthorDetailViewMapper {
    AuthorDetail findOneById(@Param("id") long id);
}
