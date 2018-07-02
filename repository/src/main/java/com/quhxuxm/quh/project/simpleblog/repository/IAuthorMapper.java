package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.Author;
import com.quhxuxm.quh.project.simpleblog.domain.AuthorAdditionalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorMapper {
    Author findAuthorByAuthenticationId(
            @Param("authenticationId")
                    long authenticationId);

    Author getAuthorById(
            @Param("id")
                    long authorId);

    void create(Author author);

    void assignAuthentication(
            @Param("authentication")
                    Authentication authentication,
            @Param("author")
                    Author author);

    void assignDefaultAnthology(
            @Param("anthology")
                    Anthology anthology,
            @Param("author")
                    Author author);

    boolean isExist(
            @Param("id")
                    long id);

    AuthorAdditionalInfo getAdditionalInfo(
            @Param("authorId")
                    long authorId);

    List<AuthorAdditionalInfo> getAdditionalInfoList(
            @Param("authorIdList")
                    List<Long> authorIdList);

    void createAdditionalInfo(AuthorAdditionalInfo additionalInfo);

    void updateAdditionalInfo(AuthorAdditionalInfo additionalInfo);

    boolean isNickNameExist(
            @Param("nickName")
                    String nickName);
}
