package com.tongwen.repository;

import com.tongwen.WebInitializer;
import com.tongwen.domain.Authentication;
import com.tongwen.domain.Author;
import com.tongwen.service.api.IAnthologyService;
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
    @Autowired
    private IAnthologyService anthologyService;

    @Test
    public void testService() {
        try {
            this.authorService.register("1@1.com", "password", "user1",
                    Authentication.Type.EMAIL,
                    "Default AnthologyReadDetail Title",
                    "Default AnthologyReadDetail Summary");
            this.authorService.register("2@1.com", "password", "user2",
                    Authentication.Type.EMAIL,
                    "Default AnthologyReadDetail Title",
                    "Default AnthologyReadDetail Summary");
            this.authorService.register("3@1.com", "password", "user3",
                    Authentication.Type.EMAIL,
                    "Default AnthologyReadDetail Title",
                    "Default AnthologyReadDetail Summary");
            this.authorService.register("4@1.com", "password", "user4",
                    Authentication.Type.EMAIL,
                    "Default AnthologyReadDetail Title",
                    "Default AnthologyReadDetail Summary");
            Authentication author1Authentication = this.authenticationService
                    .authenticate("1@1.com", Authentication.Type.EMAIL);
            System.out.println(author1Authentication.getId());
            System.out.println(author1Authentication.getNickName());
            System.out.println(author1Authentication.getPassword());
            System.out.println(author1Authentication.getToken());
            Author author1 = this.authorService
                    .getAuthor(author1Authentication);
            ArticleEditDetail articleEditDetail1 = new ArticleEditDetail();

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
