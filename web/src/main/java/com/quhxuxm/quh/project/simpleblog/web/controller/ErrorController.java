package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.web.exception.WebApiException;
import com.quhxuxm.quh.project.simpleblog.web.result.WebApiResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @ExceptionHandler(value = {WebApiException.class})
    @ResponseBody
    @RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebApiResult handleError(WebApiException e) {
        WebApiResult result = new WebApiResult();
        result.setStatus(WebApiResult.Status.SYSTEM_ERROR);
        result.setPayload(e.getMessage());
        return result;
    }
}
