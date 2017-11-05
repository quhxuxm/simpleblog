<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <c:url value="/css/login.css" var="loginCssUrl"/>
        <link href="${loginCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav showSearchBtn="false" showWriteArticleBtn="false"
                     showLoginAndRegisterBtn="false"/>
        <main>
            <div class="login-register-form-wrapper">
                <div class="login-register-select-btn-wrapper">
                    <a href="#" class="login active">
                        <fmt:message key="jsp.login.tab.title.login"/>
                    </a>
                    <c:url var="registerUrl" value="/register"/>
                    <a href="${registerUrl}" class="register">
                        <fmt:message key="jsp.login.tab.title.register"/>
                    </a>
                </div>
                <c:if test="${fn:startsWith(param.get('status'), 'ERROR_') }">
                    <div class="message-box error">
                        <ul>
                            <li>
                                <fmt:message
                                        key="jsp.login.message.failToLogin"/>
                            </li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${fn:startsWith(param.get('status'), 'REGISTER_SUCCESS') }">
                    <div class="message-box success">
                        <ul>
                            <li>
                                <fmt:message
                                        key="jsp.login.message.successToRegister"/>
                            </li>
                        </ul>
                    </div>
                </c:if>
                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}" method="post">
                    <div class="form-field-wrapper email">
                        <span class="fa fa-envelope form-field-icon"></span>
                        <fmt:message
                                key="jsp.loginAndRegister.input.placeholder.email"
                                var="emailPlaceholder"/>
                        <input type="email" placeholder="${emailPlaceholder}" name="username"/>
                    </div>
                    <div class="form-field-wrapper password">
                        <span class="fa fa-lock form-field-icon"></span>
                        <fmt:message
                                key="jsp.loginAndRegister.input.placeholder.password"
                                var="passwordPlaceholder"/>
                        <input type="password"
                               placeholder="${passwordPlaceholder}" name="password"/>
                    </div>
                    <div class="form-field-wrapper remember-me">
                        <label class="field-label">
                            <input type="checkbox"/><fmt:message
                                key="jsp.login.input.label.rememberMe"/>
                        </label>
                        <a href="#" class="field-link">
                            <fmt:message
                                    key="jsp.login.input.label.forgetPassword"/>
                        </a>
                    </div>
                    <button type="submit" formnovalidate="true">
                        <fmt:message key="jsp.login.btn.login"/>
                    </button>
                </form>
            </div>
        </main>
        <tongwen:footer/>
    </body>
</html>
