package com.quhxuxm.quh.project.simpleblog.repository;

import javax.annotation.Resource.AuthenticationType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.quhxuxm.quh.project.simpleblog.Main;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.ISecurityService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class TestRepository {
    @Autowired
    private ISecurityService securityService;

    @Test
    public void testRegisterAuthor() throws ServiceException {
        for (int i = 0; i < 100; i++) {
            this.securityService.register("toeken" + i, "password" + i, "nickName" + i,
                    Authentication.Type.values()[i % AuthenticationType.values().length]);
        }
    }
}
