package com.tongwen.web.controller;

import com.tongwen.service.api.IArticleService;
import com.tongwen.service.api.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The index controller
 */
@Controller
public class IndexController {
    private final IAuthorService authorService;
    private final IArticleService articleService;

    @Autowired
    public IndexController(IAuthorService authorService, IArticleService articleService) {
        this.authorService = authorService;
        this.articleService = articleService;
    }

    /**
     * Display the index page
     *
     * @return The index page.
     */
    @RequestMapping({"/index", "/"})
    public ModelAndView showIndex() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("adviceAuthorSummaries", null);
        return result;
    }
}
