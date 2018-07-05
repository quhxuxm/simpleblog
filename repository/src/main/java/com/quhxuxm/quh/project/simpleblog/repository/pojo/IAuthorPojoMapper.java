package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Author;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorPojoMapper {
    Author findOneById(
            @Param("id")
                    long authorId);

    Author findOneByUserName(
            @Param("userName")
                    String userName);

    Author findOneByNickName(
            @Param("nickName")
                    String nickName);

    boolean isNickNameExist(
            @Param("nickName")
                    String nickName);

    boolean isUserNameExist(
            @Param("userName")
                    String userName);

    void create(Author author);

    void update(Author author);
}
