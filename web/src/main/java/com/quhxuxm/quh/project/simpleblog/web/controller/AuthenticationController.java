package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.security.AuthenticatedAuthorDetailHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private IAuthorService authorService;

    public AuthenticationController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/authenticate",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<AuthorDetailDTO> authenticate()
            throws ServiceException {
        AuthorDetailDTO authorDetailDTO = AuthenticatedAuthorDetailHolder.INSTANCE.get();
        ApiResponse<AuthorDetailDTO> result = new ApiResponse<>();
        result.setPayload(authorDetailDTO);
        return result;
    }
}
