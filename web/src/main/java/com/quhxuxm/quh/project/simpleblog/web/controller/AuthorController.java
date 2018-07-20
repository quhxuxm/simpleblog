package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorRegisterDTO;
import com.quhxuxm.quh.project.simpleblog.web.exception.ApiException;
import com.quhxuxm.quh.project.simpleblog.web.request.ApiRequest;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.FailPayload;
import com.quhxuxm.quh.project.simpleblog.web.security.AuthenticatedAuthorDetailHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;

import javax.servlet.http.HttpSession;

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

    /**
     * Initialize the authenticate author detail, refresh the session and the thread local
     *
     * @param httpSession Http Session
     * @return The initialize response.
     * @throws ServiceException The service exception
     */
    @PostMapping(value = "/initialize",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApiResponse<AuthorDetailDTO> initialize(HttpSession httpSession)
            throws ServiceException {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String token = user.getUsername();
        AuthorDetailDTO authorDetailDTO = this.authorService
                .loginByToken(token);
        httpSession.setAttribute(
                ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL,
                authorDetailDTO);
        AuthenticatedAuthorDetailHolder.INSTANCE.set(authorDetailDTO);
        ApiResponse<AuthorDetailDTO> result = new ApiResponse<>();
        result.setPayload(authorDetailDTO);
        return result;
    }

    /**
     * Return require authenticate error.
     *
     * @return The require authenticate error.
     */
    @GetMapping(value = "/authenticate")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ApiResponse<FailPayload> authenticate() {
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        FailPayload authenticationRequiredPayload = new FailPayload(
                FailPayload.Type.AUTHENTICATION_REQUIRED);
        result.setPayload(authenticationRequiredPayload);
        return result;
    }

    @RequestMapping(value = "/clear")
    @ResponseBody
    public ApiResponse<Void> clear(HttpSession session) {
        session.removeAttribute(
                ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL);
        ApiResponse<Void> result = new ApiResponse<>();
        result.setPayload(null);
        return result;
    }
}
