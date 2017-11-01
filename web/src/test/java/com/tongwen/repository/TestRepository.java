package com.tongwen.repository;

import com.tongwen.WebInitializer;
import com.tongwen.domain.Authentication;
import com.tongwen.domain.AuthenticationType;
import com.tongwen.domain.Author;
import com.tongwen.domain.Role;
import com.tongwen.repository.mapper.IAuthenticationMapper;
import com.tongwen.repository.mapper.IAuthorMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebInitializer.class)
public class TestRepository {
    @Autowired
    private IAuthenticationMapper authenticationMapper;
    @Autowired
    private IAuthorMapper authorMapper;

    @Test
    public void test() {
        Authentication auth1 = this.authenticationMapper.findAuthenticateByToken("1@1.com");
        Assert.assertEquals("1@1.com", auth1.getToken());
        Set<Role> auth1Roles = auth1.getRoles();
        for (Role role : auth1Roles) {
            System.out.println(role.getName());
        }
        System.out.println(auth1.getRegisterDate());
        Authentication auth2 = this.authenticationMapper.findAuthenticateByToken("2@1.com");
        Set<Role> auth2Roles = auth2.getRoles();
        for (Role role : auth2Roles) {
            System.out.println(role.getName());
        }
        System.out.println(auth2.getRegisterDate());
        Author author1 = this.authorMapper.findAuthorByAuthenticationId(auth1.getId());
        System.out.println(author1.getDescription());
        System.out.println(auth1.getType());
        Authentication testForInsert = new Authentication();
        testForInsert.setLastLoginDate(new Date());
        testForInsert.setRegisterDate(new Date());
        testForInsert.setNickName("test-for-insert");
        testForInsert.setPassword("password");
        testForInsert.setToken("token1");
        testForInsert.setType(AuthenticationType.EMAIL);
        this.authenticationMapper.createAuthentication(testForInsert);
        System.out.println(testForInsert.getId());
        auth1Roles.forEach(role -> {
            this.authenticationMapper.assignRoleToAuthentication(testForInsert, role);
        });
    }
}
