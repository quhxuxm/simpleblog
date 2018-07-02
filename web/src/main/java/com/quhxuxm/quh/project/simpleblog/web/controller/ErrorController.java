package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LoggerFactory
            .getLogger(ErrorController.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(HttpServletRequest httpServletRequest,
            Exception e) throws Exception {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            serviceException.printStackTrace();
            logger.error(
                    serviceException.getCode().name() + " - " + serviceException
                            .getMessage(), serviceException);
            return new ModelAndView(
                    "redirect:/error?code=" + serviceException.getCode()
                            .name());
        }
        e.printStackTrace();
        logger.error(e.getMessage(), e);
        return new ModelAndView("redirect:/error?code="
                + IConstant.IRequestParamValue.BACKEND_ERROR_CODE_DEFAULT_VALUE);
    }
}
