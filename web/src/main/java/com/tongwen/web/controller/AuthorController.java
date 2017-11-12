package com.tongwen.web.controller;

import com.tongwen.common.IConstant;
import com.tongwen.domain.Author;
import com.tongwen.domain.AuthorAdditionalInfo;
import com.tongwen.service.api.IAnthologyService;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private IAuthorService authorService;
    private IAnthologyService anthologyService;

    @Autowired
    public AuthorController(IAuthorService authorService, IAnthologyService anthologyService) {
        this.authorService = authorService;
        this.anthologyService = anthologyService;
    }

    @GetMapping("/{authorId}/view")
    public ModelAndView viewAuthor(@PathVariable("authorId") Long authorId, HttpSession session)
        throws ServiceException {
        ModelAndView result = new ModelAndView("author");
        Author author = this.authorService.getAuthor(authorId);
        Author authenticatedAuthor =
            (Author) session.getAttribute(IConstant.ISessionAttributeName.AUTHENTICATED_AUTHOR);
        if (authenticatedAuthor != null && authenticatedAuthor.getId().equals(author.getId())) {
            result.addObject("isAuthenticatedAuthor", true);
        }
        result.addObject("author", author);
        AuthorAdditionalInfo authorAdditionalInfo =
            this.authorService.getAdditionalInfo(author.getId());
        result.addObject("additionalInfo", authorAdditionalInfo);
        return result;
    }
}
