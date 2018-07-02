package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
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

    @Autowired
    public IndexController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Display the index page
     *
     * @return The index page.
     */
    @RequestMapping({ "/index", "/" })
    public ModelAndView showIndex() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("adviceAuthorSummaries", null);
        return result;
    }
}
