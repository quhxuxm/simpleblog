package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.ApiException;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.FailPayload;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
public class AuthenticationController {
    private IAuthorService authorService;

    public AuthenticationController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/authenticate",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<AuthorDetailDTO> login(
            @RequestParam("username")
                    String userName,
            @RequestParam("password")
                    String password, WebSession webSession)
            throws ServiceException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        AuthorDetailDTO authorDetailDTO = this.authorService
                .login(authentication.getPrincipal().toString());
        if (authorDetailDTO == null) {
            FailPayload loginFailPayload = new FailPayload(
                    FailPayload.Type.LOGIN_ERROR);
            throw new ApiException(loginFailPayload);
        }
        webSession.getAttributes()
                .put(ICommonConstant.SessionAttrName.AUTHOR_DETAIL,
                        authorDetailDTO);
        ApiResponse<AuthorDetailDTO> result = new ApiResponse<>();
        result.setPayload(authorDetailDTO);
        return result;
    }
}
