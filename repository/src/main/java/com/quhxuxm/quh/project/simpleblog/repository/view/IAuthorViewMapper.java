package com.quhxuxm.quh.project.simpleblog.repository.view;
import com.quhxuxm.quh.project.simpleblog.domain.view.AuthorDetail;
import org.apache.ibatis.annotations.Param;

public interface IAuthorViewMapper {
    AuthorDetail findOneDetailById(
            @Param("id")
                    long id);
}
