package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorLoginDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorRegisterDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.ApiException;
import com.quhxuxm.quh.project.simpleblog.web.request.ApiRequest;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.FailPayload;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/register",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<Long> register(
            @RequestBody
                    ApiRequest<AuthorRegisterDTO> request)
            throws ServiceException {
        Long authorId = this.authorService.register(request.getPayload());
        if (authorId == null) {
            FailPayload registerFailPayload = new FailPayload(
                    FailPayload.Type.REGISTER_ERROR_BECAUSE_OF_CREATE_AUTHOR_FAIL);
            throw new ApiException(registerFailPayload);
        }
        ApiResponse<Long> result = new ApiResponse<>();
        result.setPayload(authorId);
        return result;
    }

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<AuthorDetailDTO> login(
            ApiRequest<AuthorLoginDTO> request) throws ServiceException {
        AuthorDetailDTO authorDetailDTO = this.authorService
                .login(request.getPayload());
        if (authorDetailDTO == null) {
            FailPayload loginFailPayload = new FailPayload(
                    FailPayload.Type.LOGIN_ERROR);
            throw new ApiException(loginFailPayload);
        }
        ApiResponse<AuthorDetailDTO> result = new ApiResponse<>();
        result.setPayload(authorDetailDTO);
        return result;
    }
}
