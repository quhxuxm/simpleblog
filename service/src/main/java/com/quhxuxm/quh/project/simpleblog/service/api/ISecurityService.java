package com.quhxuxm.quh.project.simpleblog.service.api;

import com.quhxuxm.quh.project.simpleblog.domain.pojo.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;

public interface ISecurityService {
    void register(String token, String password, String nickName, Authentication.Type type) throws ServiceException;
}
