package com.quhxuxm.quh.project.simpleblog.web.result;

public class WebApiResult {
    public enum Status {
        SUCCESS,
        SYSTEM_ERROR
    }

    private Status status;
    private Object payload;

    public WebApiResult() {
        this.status = Status.SUCCESS;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
