<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="zh_CN">
    <tongwen:head>
        <jsp:attribute name="additional">
            <c:url value="/js/common/article.js" var="articleJsUrl" />
            <script type="application/javascript" src="${articleJsUrl}"></script>
            <c:url value="/css/index.css" var="indexCssUrl"/>
            <link href="${indexCssUrl}" type="text/css" rel="stylesheet"/>
            <c:url value="/js/index.js" var="indexJsUrl" />
            <script type="application/javascript" src="${indexJsUrl}"></script>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav />
        <main>
            <section class="summaries">
                <jsp:include page="/article/allPublishedArticleSummariesCollection"/>
                <c:url var="allPublishedArticleSummariesCollection" value="/article/allPublishedArticleSummariesCollection"/>
                <a href="${allPublishedArticleSummariesCollection}" class="load-more-article-btn">
                    <fmt:message key="jsp.common.link.loadMore"/>
                </a>
            </section>
            <aside>
                <ul class="operations">
                    <li>
                        <a href="#">
                            <fmt:message key="jsp.index.link.hotArticles"/>
                            <span class="fa fa-fire"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <fmt:message key="jsp.index.link.hotAnthologies"/>
                            <span class="fa fa-book"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <fmt:message key="jsp.index.link.hotLabels"/>
                            <span class="fa fa-tag"></span>
                        </a>
                    </li>
                    <li class="advice-author">
                        <a href="#">
                            <fmt:message key="jsp.index.linkOrTitle.adviceAuthors" />
                            <span class="fa fa-users"></span>
                        </a>
                    </li>
                </ul>
                <ul class="authors">
                    <li class="title">
                        <a href="#">
                            <fmt:message key="jsp.index.linkOrTitle.adviceAuthors" />
                            <span class="fa fa-refresh"></span>
                        </a>
                    </li>
                    <c:forEach items="${adviceAuthorSummaries}" var="adviceAuthorSummary">
                        <li class="author">
                            <div class="author-info-wrapper">
                                <a href="#" class="author-info-icon-wrapper">
                                    <c:choose>
                                        <c:when test="${adviceAuthorSummary.iconImageId == null}">
                                            <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:url var="authorIconImageUrl"
                                                   value="/dimage/${adviceAuthorSummary.iconImageId}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <img src="${authorIconImageUrl}">
                                </a>
                                <a href="#" class="nick-name-and-follower-number">
                                    <span class="nick-name">${adviceAuthorSummary.nickName}</span>
                                    <span class="follower-number">
                                        <fmt:message key="jsp.index.label.followupNumber">
                                            <fmt:param value="${adviceAuthorSummary.additionalInfo.followupNumber}"/>
                                        </fmt:message>
                                    </span>
                                </a>
                            </div>
                            <a href="#" class="icon-btn">
                                <span class="fa fa-plus-circle"></span>
                                <fmt:message key="jsp.common.link.followup" />
                            </a>
                        </li>
                    </c:forEach>
                    <li class="load-more-author-btn">
                        <a href="#">
                            <fmt:message key="jsp.common.link.loadMore"/>
                        </a>
                    </li>
                </ul>
            </aside>
        </main>
        <tongwen:footer/>
    </body>
</html>
