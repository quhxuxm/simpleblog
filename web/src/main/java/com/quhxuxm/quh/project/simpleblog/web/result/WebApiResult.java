package com.quhxuxm.quh.project.simpleblog.web.result;

public class WebApiResult {
    public enum Status {
        SUCCESS,
        ERROR
    }

    private Object payload;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
