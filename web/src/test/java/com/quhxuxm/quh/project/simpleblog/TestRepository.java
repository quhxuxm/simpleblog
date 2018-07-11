package com.quhxuxm.quh.project.simpleblog;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    @Autowired
    private IAuthorService securityService;
    private static Boolean DATA_CREATED = false;

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
                OptionalLong authorIdOptional = this.securityService
                        .register("token" + i, "password" + i, "nickName" + i,
                                Authentication.Type.USERNAME);
                authorIdOptional.ifPresent(id -> {
                    Set<String> tags = new HashSet<>();
                    tags.add("tag1");
                    tags.add("tag2");
                    tags.add("tag3");
                    try {
                        this.securityService.assignTagToAuthor(id, tags);
                    } catch (ServiceException e) {
                        Assert.fail(
                                "Can not assign tags to author because of exception.");
                    }
                });
            }
            TestRepository.DATA_CREATED = true;
        }
    }

    @Test
    public void testLogin() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            int index = i;
            Optional<AuthorDetail> authorDetailOptional = this.securityService
                    .login("token" + i, "password" + i,
                            Authentication.Type.USERNAME);
            authorDetailOptional.ifPresentOrElse(author -> {
                System.out.println(author.getNickName());
            }, () -> {
                Assert.fail("Can not login author: token" + index
                        + " because of not exist");
            });
        }
    }

    @Test
    public void testAssignTag() throws ServiceException {
        Optional<AuthorDetail> authorDetailOptional = this.securityService
                .login("token10", "password10", Authentication.Type.USERNAME);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        authorDetailOptional.ifPresentOrElse(author -> {
            try {
                this.securityService
                        .assignTagToAuthor(author.getAuthorId(), tags);
            } catch (ServiceException e) {
                Assert.fail(
                        "Can not assign tags to author because of exception.");
            }
        }, () -> {
            Assert.fail("Can not login author: token10 because of not exist");
        });
    }

    @Test
    public void testGetAuthorTags() throws ServiceException {
        Optional<AuthorDetail> authorDetailOptional1 = this.securityService
                .login("token27", "password27", Authentication.Type.USERNAME);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        authorDetailOptional1.ifPresentOrElse(author -> {
            try {
                this.securityService
                        .assignTagToAuthor(author.getAuthorId(), tags);
            } catch (ServiceException e) {
                Assert.fail(
                        "Can not assign tags to author because of exception.");
            }
        }, () -> {
            Assert.fail("Can not login author: token27 because of not exist");
        });
        Optional<AuthorDetail> authorDetailOptional1FromDb = this.securityService
                .login("token27", "password27", Authentication.Type.USERNAME);
        authorDetailOptional1FromDb.ifPresentOrElse(authorDetail -> {
            Assert.assertTrue(authorDetail.getTags().contains("tag1"));
            Assert.assertTrue(authorDetail.getTags().contains("tag5"));
            Assert.assertTrue(authorDetail.getTags().contains("tag7"));
            Assert.assertTrue(authorDetail.getTags().contains("tag2"));
            Assert.assertTrue(authorDetail.getTags().contains("tag3"));
        }, () -> {
            Assert.fail("Can not login author: token27 because of not exist");
        });
    }
}
