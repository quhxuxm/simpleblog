package com.quhxuxm.quh.project.simpleblog.web.result;

public class WebApiResult<PayloadType> {
    public enum Status {
        SUCCESS,
        SYSTEM_ERROR
    }

    private Status status;
    private PayloadType payload;

    public WebApiResult() {
        this.status = Status.SUCCESS;
    }

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
