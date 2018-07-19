package com.quhxuxm.quh.project.simpleblog.web.security;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private IAuthorService authorService;

    public AuthenticationSuccessHandlerImpl(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        String token = user.getUsername();
        try {
            AuthorDetailDTO authorDetailDTO = this.authorService
                    .loginByToken(token);
            request.getSession().setAttribute(
                    ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL,
                    authorDetailDTO);
            AuthenticatedAuthorDetailHolder.INSTANCE.set(authorDetailDTO);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
