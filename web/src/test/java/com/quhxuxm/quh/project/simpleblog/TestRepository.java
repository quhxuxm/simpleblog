package com.quhxuxm.quh.project.simpleblog;

import com.quhxuxm.quh.project.simpleblog.service.api.IArticleService;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    private static Boolean DATA_CREATED = false;
    @Autowired
    private IAuthorService authorService;
    @Autowired
    private IArticleService articleService;

    @Before
    public void initialize() throws ServiceException {
        if (TestRepository.DATA_CREATED) {
            return;
        }
        synchronized (TestRepository.class) {
            if (TestRepository.DATA_CREATED) {
                return;
            }
            for (int i = 0; i < 100; i++) {
                AuthorRegisterDTO authorRegisterDTO = new AuthorRegisterDTO();
                authorRegisterDTO.setToken("token" + i);
                authorRegisterDTO.setPassword("password" + i);
                authorRegisterDTO.setNickName("nickname" + i);
                Long authorId = this.authorService.register(authorRegisterDTO);
                if (authorId == null) {
                    continue;
                }
                Set<String> tags = new HashSet<>();
                tags.add("tag1");
                tags.add("tag2");
                tags.add("tag3");
                try {
                    AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
                    authorAssignTagsDTO.setAuthorId(authorId);
                    authorAssignTagsDTO.setTags(tags);
                    this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
                } catch (ServiceException e) {
                    Assert.fail("Can not assign tags to author because of "
                            + "exception.");
                }
            }
            TestRepository.DATA_CREATED = true;
        }
    }

    @Test
    public void testLogin() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            AuthorDetailDTO authorDetail = this.authorService
                    .loginByToken("token" + i);
            if (authorDetail == null) {
                Assert.fail("Can not login author: token" + i
                        + " because of not exist");
                return;
            }
            System.out.println(authorDetail.getNickName());
        }
    }

    @Test
    public void testAssignTag() throws ServiceException {
        AuthorDetailDTO authorDetail = this.authorService.loginByToken("token10");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        if (authorDetail == null) {
            Assert.fail("Can not login author: token10 because of not exist");
            return;
        }
        AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
        authorAssignTagsDTO.setAuthorId(authorDetail.getAuthorId());
        authorAssignTagsDTO.setTags(tags);
        try {
            this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
        } catch (ServiceException e) {
            Assert.fail("Can not assign tags to author because of exception.");
        }
    }

    @Test
    public void testGetAuthorTags() throws ServiceException {
        AuthorDetailDTO authorDetail1 = this.authorService.loginByToken("token27");
        if (authorDetail1 == null) {
            Assert.fail("Can not login author: token27 because of not exist");
            return;
        }
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
        authorAssignTagsDTO.setAuthorId(authorDetail1.getAuthorId());
        authorAssignTagsDTO.setTags(tags);
        try {
            this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
        } catch (ServiceException e) {
            Assert.fail("Can not assign tags to author because of exception.");
        }
        AuthorDetailDTO authorDetailFromDb = this.authorService
                .loginByToken("token27");
        if (authorDetailFromDb == null) {
            Assert.fail("Can not login author: token27 because of not exist");
            return;
        }
        Assert.assertTrue(authorDetailFromDb.getTags().contains("tag1"));
        Assert.assertTrue(authorDetailFromDb.getTags().contains("tag5"));
        Assert.assertTrue(authorDetailFromDb.getTags().contains("tag7"));
        Assert.assertTrue(authorDetailFromDb.getTags().contains("tag2"));
        Assert.assertTrue(authorDetailFromDb.getTags().contains("tag3"));
    }

    @Test
    public void testSaveArticle() throws ServiceException {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        AuthorDetailDTO authorDetail = this.authorService.loginByToken("token25");
        if (authorDetail == null) {
            Assert.fail("Can not login author: token25 because of not exist");
            return;
        }
        try {
            createArticleDTO.setTitle("title-token25");
            createArticleDTO
                    .setAnthologyId(authorDetail.getDefaultAnthologyId());
            createArticleDTO.setAuthorId(authorDetail.getAuthorId());
            createArticleDTO.setContent("content-token25");
            createArticleDTO.setSummary("summary-token25");
            this.articleService.saveArticle(createArticleDTO);
        } catch (ServiceException e) {
            Assert.fail("Can not save article because of exception.");
        }
    }

    @Test
    public void testBookmarkArticle() throws ServiceException {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        AuthorDetailDTO authorDetail = this.authorService.loginByToken("token27");
        if (authorDetail == null) {
            Assert.fail("Can not login author: token25 because of not exist");
            return;
        }
        try {
            createArticleDTO.setTitle("title-token27");
            createArticleDTO
                    .setAnthologyId(authorDetail.getDefaultAnthologyId());
            createArticleDTO.setAuthorId(authorDetail.getAuthorId());
            createArticleDTO.setContent("content-token27");
            createArticleDTO.setSummary("summary-token27");
            Set<String> tags = new HashSet<>();
            tags.add("T1");
            tags.add("T2");
            tags.add("T3");
            createArticleDTO.setTags(tags);
            Long articleId = this.articleService.saveArticle(createArticleDTO);
            if (articleId == null) {
                Assert.fail("Fail to create article.");
                return;
            }
            ArticleBookmarkDTO articleBookmarkDTO = new ArticleBookmarkDTO();
            articleBookmarkDTO.setArticleId(articleId);
            articleBookmarkDTO.setAuthorId(authorDetail.getAuthorId());
            try {
                this.articleService.bookmarkArticle(articleBookmarkDTO);
            } catch (ServiceException e) {
                Assert.fail("Fail to bookmark article because of exception.");
            }
        } catch (ServiceException e) {
            Assert.fail("Can not save article because of exception.");
        }
    }

    @Test
    public void testPraiseArticle() throws ServiceException {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        AuthorDetailDTO authorDetail = this.authorService.loginByToken("token30");
        if (authorDetail == null) {
            Assert.fail("Can not login author: token30 because of not exist");
            return;
        }
        try {
            createArticleDTO.setTitle("title-token30");
            createArticleDTO
                    .setAnthologyId(authorDetail.getDefaultAnthologyId());
            createArticleDTO.setAuthorId(authorDetail.getAuthorId());
            createArticleDTO.setContent("content-token30");
            createArticleDTO.setSummary("summary-token30");
            Set<String> tags = new HashSet<>();
            tags.add("tag3");
            tags.add("tag2");
            tags.add("T3");
            createArticleDTO.setTags(tags);
            Long articleId = this.articleService.saveArticle(createArticleDTO);
            if (articleId == null) {
                Assert.fail("Fail to create article.");
                return;
            }
            ArticlePraiseDTO articlePraiseDTO = new ArticlePraiseDTO();
            articlePraiseDTO.setArticleId(articleId);
            articlePraiseDTO.setAuthorId(authorDetail.getAuthorId());
            try {
                this.articleService.praiseArticle(articlePraiseDTO);
            } catch (ServiceException e) {
                Assert.fail("Fail to praise article because of exception.");
            }
        } catch (ServiceException e) {
            Assert.fail("Can not save article because of exception.");
        }
    }

    @Test
    public void testViewArticle() throws ServiceException {
        CreateArticleDTO createArticleDTO = new CreateArticleDTO();
        AuthorDetailDTO authorDetail = this.authorService.loginByToken("token31");
        if (authorDetail == null) {
            Assert.fail("Can not login author: token31 because of not exist");
            return;
        }
        try {
            createArticleDTO.setTitle("title-token31");
            createArticleDTO
                    .setAnthologyId(authorDetail.getDefaultAnthologyId());
            createArticleDTO.setAuthorId(authorDetail.getAuthorId());
            createArticleDTO.setContent("content-token31");
            createArticleDTO.setSummary("summary-token31");
            Set<String> tags = new HashSet<>();
            tags.add("tag3");
            tags.add("tag2");
            tags.add("T3");
            createArticleDTO.setTags(tags);
            Long articleId = this.articleService.saveArticle(createArticleDTO);
            if (articleId != null) {
                ArticleViewDTO articleViewDTO = new ArticleViewDTO();
                articleViewDTO.setArticleId(articleId);
                articleViewDTO.setAuthorId(authorDetail.getAuthorId());
                try {
                    ArticleDetailDTO articleDetailDTO = this.articleService
                            .viewArticle(articleViewDTO);
                    if (articleDetailDTO != null) {
                        System.out.println(articleDetailDTO.getTitle());
                        return;
                    }
                    Assert.fail("Fail to view article because of exception.");
                } catch (ServiceException e) {
                    Assert.fail("Fail to view article because of exception.");
                }
                return;
            }
            Assert.fail("Fail to create article.");
        } catch (ServiceException e) {
            Assert.fail("Can not save article because of exception.");
        }
    }

    @Test
    public void testListArticleSummariesOrderByBookmarkNumber()
            throws ServiceException {
        Pageable pageable = Pageable.unpaged();
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByBookmarkNumber(pageable, false);
        page.forEach(dto -> {
            System.out
                    .println(dto.getTitle() + " ,  " + dto.getBookmarkNumber());
        });
    }

    @Test
    public void testListArticleSummariesOrderByAuthorInterests()
            throws ServiceException {
        Pageable pageable = Pageable.unpaged();
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByAuthorInterests(pageable, 30L, 10,
                        false);
        page.forEach(dto -> {
            System.out
                    .println(dto.getTitle() + " ,  " + dto.getBookmarkNumber());
        });
    }

    @Test
    public void testListArticleSummariesOrderByCreateDate()
            throws ServiceException {
        Pageable pageable = Pageable.unpaged();
        Page<ArticleSummaryDTO> page = this.articleService
                .listArticleSummariesOrderByCreateDate(pageable, false);
        page.forEach(dto -> {
            System.out
                    .println(dto.getTitle() + " ,  " + dto.getBookmarkNumber());
        });
    }
}
