package com.tongwen.web.constraints;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@ConfigurationProperties(prefix = "constraints.registerAuthor")
@Component
public class RegisterAuthorConstraints {
    private int nickNameMinLength;
    private int nickNameMaxLength;
    private Pattern emailPattern;
    private int passwordMinLength;
    private int passwordMaxLength;

    public int getNickNameMinLength() {
        return nickNameMinLength;
    }

    public void setNickNameMinLength(int nickNameMinLength) {
        this.nickNameMinLength = nickNameMinLength;
    }

    public int getNickNameMaxLength() {
        return nickNameMaxLength;
    }

    public void setNickNameMaxLength(int nickNameMaxLength) {
        this.nickNameMaxLength = nickNameMaxLength;
    }

    public Pattern getEmailPattern() {
        return emailPattern;
    }

    public void setEmailPattern(Pattern emailPattern) {
        this.emailPattern = emailPattern;
    }

    public int getPasswordMinLength() {
        return passwordMinLength;
    }

    public void setPasswordMinLength(int passwordMinLength) {
        this.passwordMinLength = passwordMinLength;
    }

    public int getPasswordMaxLength() {
        return passwordMaxLength;
    }

    public void setPasswordMaxLength(int passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
    }
}