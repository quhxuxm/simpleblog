package com.quhxuxm.quh.project.simpleblog;
import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception
        .ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorAssignTagsDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorLoginDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorRegisterDTO;
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
    private IAuthorService authorService;
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
                AuthorRegisterDTO authorRegisterDTO = new AuthorRegisterDTO();
                authorRegisterDTO.setToken("token" + i);
                authorRegisterDTO.setPassword("password" + i);
                authorRegisterDTO.setNickName("nickname" + i);
                authorRegisterDTO.setType(Authentication.Type.USERNAME);
                OptionalLong authorIdOptional = this.authorService.register(
                        authorRegisterDTO);
                authorIdOptional.ifPresent(id -> {
                    Set<String> tags = new HashSet<>();
                    tags.add("tag1");
                    tags.add("tag2");
                    tags.add("tag3");
                    try {
                        AuthorAssignTagsDTO authorAssignTagsDTO = new
                                AuthorAssignTagsDTO();
                        authorAssignTagsDTO.setAuthorId(id);
                        authorAssignTagsDTO.setTags(tags);
                        this.authorService.assignTagsToAuthor(
                                authorAssignTagsDTO);
                    } catch (ServiceException e) {
                        Assert.fail(
                                "Can not assign tags to author because of " +
                                        "exception.");
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
            AuthorLoginDTO authorLoginDTO = new AuthorLoginDTO();
            authorLoginDTO.setToken("token" + i);
            authorLoginDTO.setPassword("password" + i);
            authorLoginDTO.setType(Authentication.Type.USERNAME);
            Optional<AuthorDetailDTO> authorDetailOptional = this
                    .authorService.login(
                    authorLoginDTO);
            authorDetailOptional.ifPresentOrElse(author -> {
                System.out.println(author.getNickName());
            }, () -> {
                Assert.fail(
                        "Can not login author: token" + index + " because of " +
                                "" + "" + "" + "" + "" + "" + "" + "not exist");
            });
        }
    }

    @Test
    public void testAssignTag() throws ServiceException {
        AuthorLoginDTO authorLoginDTO = new AuthorLoginDTO();
        authorLoginDTO.setToken("token10");
        authorLoginDTO.setPassword("password10");
        authorLoginDTO.setType(Authentication.Type.USERNAME);
        Optional<AuthorDetailDTO> authorDetailOptional = this.authorService
                .login(
                authorLoginDTO);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        authorDetailOptional.ifPresentOrElse(author -> {
            AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
            authorAssignTagsDTO.setAuthorId(author.getAuthorId());
            authorAssignTagsDTO.setTags(tags);
            try {
                this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
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
        AuthorLoginDTO authorLoginDTO = new AuthorLoginDTO();
        authorLoginDTO.setToken("token27");
        authorLoginDTO.setPassword("password27");
        authorLoginDTO.setType(Authentication.Type.USERNAME);
        Optional<AuthorDetailDTO> authorDetailOptional1 = this.authorService
                .login(
                authorLoginDTO);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag5");
        tags.add("tag7");
        authorDetailOptional1.ifPresentOrElse(author -> {
            AuthorAssignTagsDTO authorAssignTagsDTO = new AuthorAssignTagsDTO();
            authorAssignTagsDTO.setAuthorId(author.getAuthorId());
            authorAssignTagsDTO.setTags(tags);
            try {
                this.authorService.assignTagsToAuthor(authorAssignTagsDTO);
            } catch (ServiceException e) {
                Assert.fail(
                        "Can not assign tags to author because of exception.");
            }
        }, () -> {
            Assert.fail("Can not login author: token27 because of not exist");
        });
        Optional<AuthorDetailDTO> authorDetailOptional1FromDb = this
                .authorService.login(
                authorLoginDTO);
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
