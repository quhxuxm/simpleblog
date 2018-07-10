package com.quhxuxm.quh.project.simpleblog;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.ISecurityService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    @Autowired
    private ISecurityService securityService;

    @Test
    @Before
    public void testRegisterAuthor() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            this.securityService.register("token" + i, "password" + i, "nickName" + i, Authentication.Type.values()[i % Authentication.Type.values().length]);
        }
    }

    @Test
    public void testLogin() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            Authentication authentication = this.securityService.login("token" + i, "password" + i, Authentication.Type.values()[i % Authentication.Type.values().length]);
            System.out.println(authentication.getAuthor().getNickName());
        }
    }
}
