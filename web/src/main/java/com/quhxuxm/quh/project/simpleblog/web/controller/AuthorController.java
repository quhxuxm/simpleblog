package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorLoginDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorRegisterDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalLong;

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
    public WebApiResult register(@RequestBody AuthorRegisterDTO registerDTO) {
        try {
            OptionalLong authorIdOptional = this.authorService
                    .register(registerDTO);
            if (authorIdOptional.isPresent()) {
                WebApiResult result = new WebApiResult();
                result.setPayload(authorIdOptional.getAsLong());
                return result;
            }
            throw new WebApiException();
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }

    @PostMapping(value = "/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public WebApiResult login(AuthorLoginDTO loginDTO) {
        try {
            Optional<AuthorDetailDTO> authorDetailDTOOptional = this.authorService
                    .login(loginDTO);
            if (authorDetailDTOOptional.isPresent()) {
                WebApiResult result = new WebApiResult();
                result.setPayload(authorDetailDTOOptional.get());
                return result;
            }
            throw new WebApiException();
        } catch (ServiceException e) {
            throw new WebApiException();
        }
    }

    @GetMapping(value = "/detail",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public WebApiResult detail(@RequestParam(name = "authorid") Long authorId) {
        return null;
    }
}
