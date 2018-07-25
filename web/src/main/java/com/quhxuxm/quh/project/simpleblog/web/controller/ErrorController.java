package com.quhxuxm.quh.project.simpleblog.web.controller;

import com.quhxuxm.quh.project.simpleblog.service.api.exception.ServiceException;
import com.quhxuxm.quh.project.simpleblog.web.exception.ApiException;
import com.quhxuxm.quh.project.simpleblog.web.response.ApiResponse;
import com.quhxuxm.quh.project.simpleblog.web.response.FailPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ErrorController {
    @ExceptionHandler(value = { ApiException.class })
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleApiError(ApiException e) {
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        result.setPayload(e.getPayload());
        return result;
    }

    @ExceptionHandler(value = { ServiceException.class })
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleServiceError(ServiceException e) {
        FailPayload systemErrorPayload = new FailPayload(
                FailPayload.Type.SERVICE_ERROR_UNKNOWN);
        systemErrorPayload.setMessage(e.getMessage());
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        result.setPayload(systemErrorPayload);
        return result;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public ApiResponse<FailPayload> handleUnknownError(ServiceException e) {
        FailPayload systemErrorPayload = new FailPayload(
                FailPayload.Type.ERROR_UNKNOWN);
        systemErrorPayload.setMessage(e.getMessage());
        ApiResponse<FailPayload> result = new ApiResponse<>();
        result.setStatus(ApiResponse.Status.FAIL);
        result.setPayload(systemErrorPayload);
        return result;
    }
}
