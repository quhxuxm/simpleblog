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
            <c:url value="/css/anthology.css" var="anthologyCssUrl"/>
            <link href="${anthologyCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav/>
        <main>

            <section class="summaries">
                <article class="anthology-summary">
                    <!--
                    <c:if test="${anthologyDetail.coverImageId != null}">
                        <a class="anthology-cover"></a>
                    </c:if>
                    -->

                    <a class="anthology-cover"></a>

                    <div class="anthology-content">
                        <header class="anthology-title">
                            <h1><c:out escapeXml="true" value="${anthologyDetail.title}" /></h1>
                        </header>
                        <footer>
                            <fmt:message key="jsp.anthology.label.totalFollowup">
                                <fmt:param value="${anthologyAdditionalInfo.bookmarkNumber}"/>
                            </fmt:message>
                        </footer>
                    </div>
                    <div class="anthology-actions-wrapper">
                        <c:choose >
                            <c:when test="${isAnthologyBelongToAuthor}">
                                <a href="#" class="icon-btn reverse lg">
                                    <span class="fa fa-pencil"></span>
                                    <fmt:message key="jsp.common.link.update"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" class="icon-btn reverse lg">
                                    <span class="fa fa-plus-circle"></span>
                                    <fmt:message key="jsp.common.link.followup"/>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </article>

                <jsp:include
                        page="/article/anthologyArticleSummariesCollection/${anthologyDetail.id}"/>
                <c:url var="anthologyArticleSummariesCollection"
                       value="/article/anthologyArticleSummariesCollection/${anthologyDetail.id}"/>
                <a href="${anthologyArticleSummariesCollection}"
                   class="load-more-article-btn">
                    <fmt:message key="jsp.common.link.loadMore"/>
                </a>
            </section>
            <aside>
                <article>
                    <header>
                        <h1>
                            <fmt:message key="jsp.anthology.label.anthologyDescription"/>
                        </h1>
                    </header>
                    <section>
                        <c:out value="${anthologyDetail.summary}" escapeXml="true"/>
                    </section>
                </article>
                <ul class="authors">
                    <li class="title">
                        <h1>
                            <fmt:message key="jsp.anthology.label.participant" />
                        </h1>
                    </li>
                    <li class="author">
                        <div class="author-info-wrapper">
                            <a href="#" class="author-info-icon-wrapper">
                                <c:choose>
                                    <c:when test="${anthologyDetail.authorIconImageId == null}">
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
