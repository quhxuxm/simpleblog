package com.tongwen.web.validator;

import com.tongwen.service.api.IAuthorService;
import com.tongwen.service.exception.ServiceException;
import com.tongwen.web.constraints.RegisterAuthorConstraints;
import com.tongwen.web.request.RegisterAuthorForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterAuthorRequestValidator implements Validator {
    private static final Logger logger = LoggerFactory
            .getLogger(RegisterAuthorRequestValidator.class);
    private final IAuthorService authorService;
    private RegisterAuthorConstraints registerAuthorConstraints;
    @Value("jsp.register.errorMessage.nickName.empty")
    private String nickNameEmptyErrorCode;
    @Value("jsp.register.errorMessage.nickName.minSize")
    private String nickNameMinSizeErrorCode;
    @Value("jsp.register.errorMessage.nickName.maxSize")
    private String nickNameMaxSizeErrorCode;
    @Value("jsp.register.errorMessage.nickName.exist")
    private String nickNameAlreadyExistErrorCode;
    @Value("jsp.register.errorMessage.email.empty")
    private String emailEmptyErrorCode;
    @Value("jsp.register.errorMessage.email.format")
    private String emailFormatErrorCode;
    @Value("jsp.register.errorMessage.email.exist")
    private String emailAlreadyExistErrorCode;
    @Value("jsp.register.errorMessage.systemError")
    private String systemExceptionErrorCode;
    @Value("jsp.register.errorMessage.password.empty")
    private String passwordEmptyErrorCode;
    @Value("jsp.register.errorMessage.password.minSize")
    private String passwordMinSizeErrorCode;
    @Value("jsp.register.errorMessage.password.maxSize")
    private String passwordMaxSizeErrorCode;

    public RegisterAuthorRequestValidator(IAuthorService authorService,
            RegisterAuthorConstraints registerAuthorConstraints) {
        this.authorService = authorService;
        this.registerAuthorConstraints = registerAuthorConstraints;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterAuthorForm.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterAuthorForm registerAuthorForm = (RegisterAuthorForm) target;
        if (StringUtils.isEmpty(registerAuthorForm.getNickName())) {
            errors.rejectValue("nickName", this.nickNameEmptyErrorCode);
            return;
        }
        int nickNameLength = registerAuthorForm.getNickName().trim()
                .length();
        if (nickNameLength < this.registerAuthorConstraints
                .getNickNameMinLength()) {
            errors.rejectValue("nickName", this.nickNameMinSizeErrorCode,
                    new Object[] {
                            this.registerAuthorConstraints.getNickNameMinLength() },
                    null);
            return;
        }
        if (nickNameLength > this.registerAuthorConstraints
                .getNickNameMaxLength()) {
            errors.rejectValue("nickName", this.nickNameMaxSizeErrorCode,
                    new Object[] {
                            this.registerAuthorConstraints.getNickNameMaxLength() },
                    null);
            return;
        }
        try {
            if (this.authorService
                    .isNickNameExist(registerAuthorForm.getNickName())) {
                errors.rejectValue("nickName",
                        this.nickNameAlreadyExistErrorCode);
            }
        } catch (ServiceException e) {
            logger.error(
                    "Fail to validate user because of exception on querying nick name.",
                    e);
            errors.reject(this.systemExceptionErrorCode);
        }
        if (StringUtils.isEmpty(registerAuthorForm.getEmail())) {
            errors.rejectValue("email", this.emailEmptyErrorCode);
            return;
        }
        if (!this.registerAuthorConstraints.getEmailPattern()
                .matcher(registerAuthorForm.getEmail()).matches()) {
            errors.rejectValue("email", this.emailFormatErrorCode);
            return;
        }
        try {
            if (this.authorService
                    .isEmailExist(registerAuthorForm.getEmail())) {
                errors.rejectValue("email", this.emailAlreadyExistErrorCode);
            }
        } catch (ServiceException e) {
            logger.error(
                    "Fail to validate user because of exception on querying email.",
                    e);
            errors.reject(this.systemExceptionErrorCode);
        }
        if (StringUtils.isEmpty(registerAuthorForm.getPassword())) {
            errors.rejectValue("email", this.passwordEmptyErrorCode);
            return;
        }
        int passwordLength = registerAuthorForm.getPassword().trim()
                .length();
        if (passwordLength < this.registerAuthorConstraints
                .getPasswordMinLength()) {
            errors.rejectValue("password", this.passwordMinSizeErrorCode,
                    new Object[] {
                            this.registerAuthorConstraints.getPasswordMinLength() },
                    null);
            return;
        }
        if (passwordLength > this.registerAuthorConstraints
                .getPasswordMaxLength()) {
            errors.rejectValue("password", this.passwordMaxSizeErrorCode,
                    new Object[] {
                            this.registerAuthorConstraints.getPasswordMaxLength() },
                    null);
        }
    }
}
