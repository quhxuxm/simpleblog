<%@ tag pageEncoding="UTF-8" body-content="scriptless" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="additional" fragment="true" required="false"  %>
<%@ attribute name="showSearchBtn" type="java.lang.Boolean" required="false" %>
<%@ attribute name="showLoginAndRegisterBtn" type="java.lang.Boolean" required="false"  %>
<%@ attribute name="showWriteArticleBtn" type="java.lang.Boolean" required="false"%>
<c:if test="${showSearchBtn == null}">
    <c:set var="showSearchBtn" value="true"/>
</c:if>
<c:if test="${showLoginAndRegisterBtn == null}">
    <c:set var="showLoginAndRegisterBtn" value="true"/>
</c:if>
<c:if test="${showWriteArticleBtn == null}">
    <c:set var="showWriteArticleBtn" value="true"/>
</c:if>
<nav>
    <ul>
        <li class="logo-link-wrapper">
            <c:url var="indexPageUrl" value="/" />
            <a href="${indexPageUrl}" class="logo-link">
                <h1>
                    <fmt:message key="jsp.nav.logo"/>
                </h1>
            </a>
        </li>
        <c:if test="${showLoginAndRegisterBtn}">
            <c:if test="${sessionScope.authenticatedAuthor == null}">
                <li class="login-register-btn-wrapper">
                    <c:url var="loginUrl" value="/login"/>
                    <a href="${loginUrl}" class="login-register-btn">
                        <fmt:message key="jsp.nav.link.loginOrRegister" />
                    </a>
                </li>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.authenticatedAuthor != null}">
            <li class="author-actions-btn-wrapper">
                <a href="#" class="author-actions-btn icon-img-wrapper md">
                    <c:choose>
                        <c:when test="${sessionScope.authenticatedAuthor.iconImageId ==null}">
                            <c:url var="authorIconImageUrl"
                                   value="/image/defaultAuthorIcon.jpg"/>
                        </c:when>
                        <c:otherwise>
                            <c:url var="authorIconImageUrl"
                                   value="/dimage/${sessionScope.authenticatedAuthor.iconImageId}"/>
                        </c:otherwise>
                    </c:choose>
                    <img src="${authorIconImageUrl}" />
                </a>
                <ul class="author-actions-list">
                    <li>
                        <c:url var="myAnthologyUrl" value="/antology/list"/>
                        <a href="${myAnthologyUrl}" class="action">
                            <span class="fa fa-book"></span>
                            <fmt:message key="jsp.nav.authorActionList.link.myAnthology"/>
                        </a>
                    </li>
                    <li>
                        <c:url var="authorSeetingUrl" value="/author/setting"/>
                        <a href="${authorSeetingUrl}"  class="action">
                            <span class="fa fa-gear"></span>
                            <fmt:message key="jsp.nav.authorActionList.link.setting"/>
                        </a>
                    </li>
                    <li>
                        <c:url var="logoutUrl" value="/logout"/>
                        <a href="${logoutUrl}"  class="action">
                            <span class="fa fa-sign-out"></span>
                            <fmt:message key="jsp.nav.authorActionList.link.logout"/>
                        </a>
                    </li>
                </ul>
            </li>
        </c:if>
        <c:if test="${showWriteArticleBtn}">
            <li class="write-article-btn-wrapper">
                <c:url var="writeArticleUrl" value="/article/write"/>
                <a href="${writeArticleUrl}" class="write-article-btn">
                    <fmt:message key="jsp.nav.link.writeArticle" />
                </a>
            </li>
        </c:if>
        <c:if test="${showSearchBtn}">
            <li class="search-btn-wrapper">
                <a href="#" class="fa fa-search search-btn"></a>
            </li>
        </c:if>
    </ul>
</nav>
