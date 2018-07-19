package com.quhxuxm.quh.project.simpleblog.web.filter;

import com.quhxuxm.quh.project.simpleblog.common.ICommonConstant;
import com.quhxuxm.quh.project.simpleblog.service.dto.AuthorDetailDTO;
import com.quhxuxm.quh.project.simpleblog.web.security.AuthenticatedAuthorDetailHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InitializeAuthenticatedAuthorDetailHolderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        AuthorDetailDTO authorDetailDTOFromSession = (AuthorDetailDTO) httpSession
                .getAttribute(
                        ICommonConstant.SessionAttrName.AUTHENTICATED_AUTHOR_DETAIL);
        if (authorDetailDTOFromSession == null) {
            chain.doFilter(request, response);
            return;
        }
        AuthenticatedAuthorDetailHolder.INSTANCE
                .set(authorDetailDTOFromSession);
        chain.doFilter(request, response);
        AuthenticatedAuthorDetailHolder.INSTANCE.clear();
    }

    @Override
    public void destroy() {
    }
}
