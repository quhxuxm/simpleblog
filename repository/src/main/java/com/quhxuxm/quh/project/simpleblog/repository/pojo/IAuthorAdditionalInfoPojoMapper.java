package com.quhxuxm.quh.project.simpleblog.repository.pojo;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AuthorAdditionalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorAdditionalInfoPojoMapper {
    AuthorAdditionalInfo findOneByAuthorId(@Param("authorId") long authorId);

    void create(AuthorAdditionalInfo additionalInfo);

    void update(AuthorAdditionalInfo additionalInfo);
}
