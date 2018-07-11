package com.quhxuxm.quh.project.simpleblog;
import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    @Autowired
    private IAuthorService securityService;
    private static boolean dataCreated;

    @Before
    public void createDate() throws ServiceException {
        if (!this.dataCreated) {
            for (int i = 0; i < 100; i++) {
                this.securityService.register("token" + i, "password" + i,
                        "nickName" + i,
                        Authentication.Type.values()[i % Authentication.Type
                                .values().length]);
            }
        }
        this.dataCreated = true;
    }

    @Test
    public void testLogin() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            AuthorDetail authorDetail = this.securityService.login("token" + i,
                    "password" + i, Authentication.Type.USERNAME);
            System.out.println(authorDetail.getNickName());
        }
    }

    @Test
    public void testAssignTag() throws ServiceException {
        AuthorDetail authorDetail = this.securityService.login("token10",
                "password10", Authentication.Type.USERNAME);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        this.securityService.assignTagToAuthor(authorDetail.getAuthorId(),
                tags);
    }
}
