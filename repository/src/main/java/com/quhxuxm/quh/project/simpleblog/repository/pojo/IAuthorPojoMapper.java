package com.quhxuxm.quh.project.simpleblog.repository.pojo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Author;

@Repository
public interface IAuthorPojoMapper {
    Author findOneById(@Param("id") long authorId);

    Author findOneByUserName(@Param("userName") String userName);

    Author findOneByNickName(@Param("nickName") String nickName);

    boolean isNickNameExist(@Param("nickName") String nickName);

    void create(Author author);

    void update(Author author);
}
