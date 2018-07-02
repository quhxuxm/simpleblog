package com.quhxuxm.quh.project.simpleblog.web.response;

public class LoginRequiredResponse {
    private boolean loginRequired;
    private String location;

    public LoginRequiredResponse() {
        this.loginRequired = true;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(boolean loginRequired) {
        this.loginRequired = loginRequired;
    }
}
