package com.tongwen.repository;

import com.tongwen.WebInitializer;
import com.tongwen.domain.Authentication;
import com.tongwen.service.api.IAuthenticationService;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebInitializer.class)
public class TestRepository {
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IAuthenticationService authenticationService;

    @Test
    public void testService() {
        try {
            this.authorService.register("1@1.com", "password", "user1",
                    Authentication.Type.EMAIL, "Default Anthology Title",
                    "Default Anthology Summary");
            this.authorService.register("2@1.com", "password", "user2",
                    Authentication.Type.EMAIL, "Default Anthology Title",
                    "Default Anthology Summary");
            this.authorService.register("3@1.com", "password", "user3",
                    Authentication.Type.EMAIL, "Default Anthology Title",
                    "Default Anthology Summary");
            this.authorService.register("4@1.com", "password", "user4",
                    Authentication.Type.EMAIL, "Default Anthology Title",
                    "Default Anthology Summary");
            Authentication authenticatedAuthor1 = this.authenticationService
                    .authenticate("1@1.com", Authentication.Type.EMAIL);
            System.out.println(authenticatedAuthor1.getId());
            System.out.println(authenticatedAuthor1.getNickName());
            System.out.println(authenticatedAuthor1.getPassword());
            System.out.println(authenticatedAuthor1.getToken());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
