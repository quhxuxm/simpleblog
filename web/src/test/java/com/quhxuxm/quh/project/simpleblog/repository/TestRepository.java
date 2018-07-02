package com.quhxuxm.quh.project.simpleblog.repository;

import com.quhxuxm.quh.project.simpleblog.Main;
import com.quhxuxm.quh.project.simpleblog.domain.*;
import com.tongwen.domain.*;
import com.quhxuxm.quh.project.simpleblog.service.api.IAnthologyService;
import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthenticationService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private IAnthologyService anthologyService;
    @Autowired
    private IArticleService articleService;

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
            System.out.println(author1Authentication.getPassword());
            System.out.println(author1Authentication.getToken());
            Author author1 = this.authorService
                    .getAuthenticatedAuthor(author1Authentication.getId());
            for (int i = 0; i < 1000; i++) {
                Article article = new Article();
                article.setTitle("Title " + i);
                article.setSummary("Summary " + i);
                article.setContent("Content " + i);
                article.setCreateDate(new Date());
                article.setUpdateDate(new Date());
                article.setPublishDate(
                        new Date(article.getCreateDate().getTime() + 36000));
                article.setAnthologyId(author1.getDefaultAnthologyId());
                this.articleService.create(article, author1, "/dimage");
            }
            AnthologyDetail author1DefaultAnthologyDetail = this.anthologyService
                    .getAnthologyDetail(author1.getDefaultAnthologyId());
            List<ArticleSummary> articlesInAuthor1DefaultAnthology = this.articleService
                    .getPublishedArticleSummariesInAnthology(
                            author1DefaultAnthologyDetail.getId(), 0, true);
            for (ArticleSummary articleSummary : articlesInAuthor1DefaultAnthology) {
                System.out.println(articleSummary.getSummary());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println("###################################");
        try {
            List<ArticleSummary> articleSummaries = this.articleService
                    .getSummariesOrderByPublishDate(0, true);
            Map<Long, ArticleAdditionalInfo> additionalInfoMap = this.articleService
                    .getAdditionalInfoList(articleSummaries);
            for (ArticleSummary summary : articleSummaries) {
                System.out.println(summary.getSummary());
                System.out.println(
                        "View: " + additionalInfoMap.get(summary.getId())
                                .getViewNumber());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
