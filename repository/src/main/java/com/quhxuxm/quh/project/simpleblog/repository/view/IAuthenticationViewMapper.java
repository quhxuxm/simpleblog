package com.quhxuxm.quh.project.simpleblog.repository.view;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import com.quhxuxm.quh.project.simpleblog.domain.view.AuthenticationSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationViewMapper {
    AuthenticationSummary findOneSummaryById(
            @Param("id")
                    long id);

    AuthenticationSummary findOneSummaryByTokenAndType(
            @Param("token")
                    String token,
            @Param("type")
                    Authentication.Type type);
}
