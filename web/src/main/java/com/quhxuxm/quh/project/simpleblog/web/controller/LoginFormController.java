package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.web.response.LoginRequiredResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

/**
 * The controller used to handle different login form
 */
@Controller
@RequestMapping("/login")
public class LoginFormController {
    private ServletContext servletContext;

    public LoginFormController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * The ajax login form
     *
     * @return The json body of ajax login form
     */
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public LoginRequiredResponse ajax() {
        LoginRequiredResponse loginRequiredResponse = new LoginRequiredResponse();
        loginRequiredResponse.setLocation(this.servletContext.getContextPath() + "/login");
        return loginRequiredResponse;
    }

    /**
     * The html login form
     *
     * @return The jsp path
     */
    @RequestMapping(produces = "text/html")
    public String html() {
        return "login";
    }
}
