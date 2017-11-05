<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="zh_CN">
    <tongwen:head>
        <jsp:attribute name="additional">
            <c:url value="/js/common/article.js" var="articleJsUrl"/>
            <script type="application/javascript"
                    src="${articleJsUrl}"></script>
            <c:url value="/css/login-and-register.css"
                   var="loginAndRegisterCssUrl"/>
            <link href="${loginAndRegisterCssUrl}" type="text/css"
                  rel="stylesheet"/>
            <c:url value="/css/register.css" var="registerCssUrl"/>
        <link href="${registerCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav showSearchBtn="false" showWriteArticleBtn="false"
                     showLoginAndRegisterBtn="false"/>
        <main>
            <div class="login-register-form-wrapper">
                <div class="login-register-select-btn-wrapper">
                    <c:url var="loginUrl" value="/login"/>
                    <a href="${loginUrl}" class="login">
                        <fmt:message key="jsp.login.tab.title.login"/>
                    </a>
                    <a href="#" class="register active">
                        <fmt:message key="jsp.login.tab.title.register"/>
                    </a>
                </div>
                <spring:hasBindErrors name="registerAuthorForm">
                    <div class="message-box error">
                        <ul>
                            <spring:bind path="registerAuthorForm.*"
                                         ignoreNestedPath="true">
                                <c:forEach items="${status.errorMessages}"
                                           var="errorMessage">
                                    <li>${errorMessage}</li>
                                </c:forEach>
                            </spring:bind>
                        </ul>
                    </div>
                </spring:hasBindErrors>
                <c:url var="registerUrl" value="/register"/>
                <form action="${registerUrl}" method="post">
                    <div class="form-field-wrapper nick-name">
                        <span class="fa fa-user form-field-icon"></span>
                        <fmt:message
                                key="jsp.loginAndRegister.input.placeholder.nickname"
                                var="nickNamePlaceholder"/>
                        <spring:bind path="registerAuthorForm.nickName">
                            <c:set value="${status.actualValue}"
                                   var="nickNameFieldValue"/>
                        </spring:bind>
                        <input type="text" placeholder="${nickNamePlaceholder}"
                               name="nickName" value="${nickNameFieldValue}"/>
                    </div>
                    <div class="form-field-wrapper email">
                        <span class="fa fa-envelope form-field-icon"></span>
                        <fmt:message
                                key="jsp.loginAndRegister.input.placeholder.email"
                                var="emailPlaceholder"/>
                        <spring:bind path="registerAuthorForm.email">
                            <c:set value="${status.actualValue}"
                                   var="emailFieldValue"/>
                        </spring:bind>
                        <input type="email" placeholder="${emailPlaceholder}"
                               name="email" value="${emailFieldValue}"/>
                    </div>
                    <div class="form-field-wrapper password">
                        <span class="fa fa-lock form-field-icon"></span>
                        <fmt:message
                                key="jsp.loginAndRegister.input.placeholder.password"
                                var="passwordPlaceholder"/>
                        <spring:bind path="registerAuthorForm.password">
                            <c:set value="${status.actualValue}"
                                   var="passwordFieldValue"/>
                        </spring:bind>
                        <input type="password"
                               placeholder="${passwordPlaceholder}"
                               name="password" value="${passwordFieldValue}"/>
                    </div>
                    <div class="form-field-wrapper accept-protocol">
                        <label class="field-label">
                            <input type="checkbox"/>
                            <fmt:message
                                    key="jsp.register.input.label.acceptProtocol"/>
                        </label>
                        <a href="#" class="field-link">
                            <fmt:message
                                    key="jsp.register.link.authorProtocol"/>
                        </a>
                    </div>
                    <button type="submit" formnovalidate="true">
                        <fmt:message key="jsp.register.btn.register"/>
                    </button>
                </form>
            </div>
        </main>
        <tongwen:footer/>
    </body>
</html>
