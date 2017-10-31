package com.tongwen.repository;

import com.tongwen.WebInitializer;
import com.tongwen.domain.Author;
import com.tongwen.domain.Role;
import com.tongwen.repository.mapper.IAuthorMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebInitializer.class)
public class TestRepository {
    @Autowired
    private IAuthorMapper authorMapper;

    @Test
    public void testFindAuthenticationByEmail() {
        Author author1 = this.authorMapper.findAuthorForAuthenticateByEmail("1@1.com");
        Assert.assertEquals("1@1.com", author1.getEmail());
        Set<Role> author1Roles = author1.getRoles();
        for (Role role : author1Roles) {
            System.out.println(role.getName());
        }
        System.out.println(author1.getRegisterDate());
        Author author2 = this.authorMapper.findAuthorForAuthenticateByEmail("2@1.com");
        Set<Role> author2Roles = author2.getRoles();
        for (Role role : author2Roles) {
            System.out.println(role.getName());
        }
        System.out.println(author2.getRegisterDate());
    }
}
