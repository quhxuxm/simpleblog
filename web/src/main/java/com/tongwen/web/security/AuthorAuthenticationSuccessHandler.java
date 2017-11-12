package com.tongwen.web.security;

import com.tongwen.common.IConstant;
import com.tongwen.domain.Author;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(AuthorAuthenticationSuccessHandler.class);
    private final IAuthorService authorService;

    @Autowired
    public AuthorAuthenticationSuccessHandler(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Object principle = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        AuthorDetails authorDetails = null;
        if (principle instanceof UserDetails) {
            authorDetails = (AuthorDetails) principle;
        }
        if (authorDetails == null) {
            logger.error(
                    "Can not get author details from security context, fail to login.");
            response.sendRedirect(String.format(
                    IConstant.IUrlFormat.LOGIN_REDIRECT_URL_FORMAT,
                    IConstant.LoginStatus.ERROR_AUTHOR_NOT_EXIST));
            return;
        }
        logger.debug("Success to get author details from security context.");
        try {
            Author author = this.authorService
                    .getAuthenticatedAuthor(authorDetails.getAuthenticationId());
            request.getSession().setAttribute(
                    IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR,
                    author);
        } catch (ServiceException e) {
            logger.error("Fail to find author from database with email: "
                            + authorDetails.getUsername() + " because of exception.",
                    e);
            response.sendRedirect(String.format(
                    IConstant.IUrlFormat.LOGIN_REDIRECT_URL_FORMAT,
                    IConstant.LoginStatus.ERROR_SYSTEM));
            return;
        }
        logger.debug(
                "Success to authenticate author with email: " + authorDetails
                        .getUsername());
        response.sendRedirect(request.getContextPath());
    }
}
