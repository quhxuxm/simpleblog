package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @ExceptionHandler(value = { WebApiException.class })
    public WebApiResult handleError(WebApiException e) {
        WebApiResult result = new WebApiResult();
        result.setStatus(WebApiResult.Status.ERROR);
        result.setPayload(e);
        return result;
    }
}
