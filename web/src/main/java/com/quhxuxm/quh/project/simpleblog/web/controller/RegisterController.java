package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.domain.Authentication;
import com.quhxuxm.quh.project.simpleblog.service.api.IAuthorService;
import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.web.request.RegisterAuthorForm;
import com.tongwen.web.validator.RegisterAuthorRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private IAuthorService authorService;
    private RegisterAuthorRequestValidator registerAuthorRequestValidator;
    private final MessageSource messageSource;

    public RegisterController(IAuthorService authorService,
        RegisterAuthorRequestValidator registerAuthorRequestValidator,
        MessageSource messageSource) {
        this.registerAuthorRequestValidator = registerAuthorRequestValidator;
        this.authorService = authorService;
        this.messageSource = messageSource;
    }

    @InitBinder
    public void initBinder(DataBinder dataBinder) {
        dataBinder.addValidators(this.registerAuthorRequestValidator);
    }

    @GetMapping
    public ModelAndView showRegister() {
        ModelAndView result = new ModelAndView("register");
        result.addObject("registerAuthorForm", new RegisterAuthorForm());
        return result;
    }

    @PostMapping
    public String register(@Validated RegisterAuthorForm request, Errors errors, Locale locale) {
        ModelAndView registerPage = new ModelAndView("register");
        if (errors.hasErrors()) {
            return "register";
        }
        try {
            this.authorService
                .register(request.getEmail(), request.getPassword(), request.getNickName(),
                    Authentication.Type.EMAIL, this.messageSource
                        .getMessage(IConstant.MessageCode.ANTHOLOGY_DEFAULT_TITLE_MESSAGE_CODE,
                            null, locale), this.messageSource
                        .getMessage(IConstant.MessageCode.ANTHOLOGY_DEFAULT_SUMMARY_MESSAGE_CODE,
                            null, locale));
        } catch (ServiceException e) {
            logger.error("Fail to register user because of exception.", e);
            errors.reject(IConstant.MessageCode.SYSTEM_ERROR_MESSAGE_CODE);
            return "register";
        }
        return "redirect:" + String.format(IConstant.IUrlFormat.LOGIN_REDIRECT_URL_FORMAT,
            IConstant.LoginStatus.REGISTER_SUCCESS);
    }
}
