package com.tongwen.web.controller;

import com.tongwen.common.IConstant;
import com.tongwen.domain.Authentication;
import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import com.tongwen.web.request.RegisterAuthorForm;
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

@Controller
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private IAuthorService authorService;
    private RegisterAuthorRequestValidator registerAuthorRequestValidator;
    private MessageSource messageSource;

    public RegisterController(IAuthorService authorService,
        RegisterAuthorRequestValidator registerAuthorRequestValidator) {
        this.registerAuthorRequestValidator = registerAuthorRequestValidator;
        this.authorService = authorService;
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
    public String register(@Validated RegisterAuthorForm request, Errors errors) {
        ModelAndView registerPage = new ModelAndView("register");
        if (errors.hasErrors()) {
            return "register";
        }
        try {
            this.authorService
                .register(request.getEmail(), request.getPassword(), request.getNickName(),
                    Authentication.Type.EMAIL,
                    IConstant.MessageCode.ANTHOLOGY_DEFAULT_TITLE_MESSAGE_CODE,
                    IConstant.MessageCode.ANTHOLOGY_DEFAULT_SUMMARY_MESSAGE_CODE);
        } catch (ServiceException e) {
            logger.error("Fail to register user because of exception.", e);
            errors.reject(IConstant.MessageCode.SYSTEM_ERROR_MESSAGE_CODE);
            return "register";
        }
        return "redirect:" + String.format(IConstant.IUrlFormat.LOGIN_REDIRECT_URL_FORMAT,
            IConstant.LoginStatus.REGISTER_SUCCESS);
    }
}
