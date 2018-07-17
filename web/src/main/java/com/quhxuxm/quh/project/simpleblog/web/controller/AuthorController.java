package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorLoginDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorRegisterDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/register")
    @JsonView
    public WebApiResult register(AuthorRegisterDTO registerDTO) {
        try {
            OptionalLong authorIdOptional = this.authorService
                    .register(registerDTO);
            if (authorIdOptional.isPresent()) {
                WebApiResult result = new WebApiResult();
                result.setStatus(WebApiResult.Status.SUCCESS);
                result.setPayload(authorIdOptional.getAsLong());
                return result;
            }
            throw new WebApiException();
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }

    @PostMapping(value = "/login")
    @JsonView
    public WebApiResult login(AuthorLoginDTO loginDTO) {
        try {
            Optional<AuthorDetailDTO> authorDetailDTOOptional = this.authorService
                    .login(loginDTO);
            if (!authorDetailDTOOptional.isPresent()) {
                throw new WebApiException();
            }
            WebApiResult result = new WebApiResult();
            result.setStatus(WebApiResult.Status.SUCCESS);
            result.setPayload(authorDetailDTOOptional.get());
            return result;
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }
}
