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
            <c:url value="/css/author.css" var="authorCssUrl"/>
            <link href="${authorCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav/>
        <main>

            <section class="summaries">
                <article class="author-summary">

                    <c:choose>
                        <c:when test="${author.iconImageId==null}">
                            <c:url var="authorIconImageUrl"
                                   value="/image/defaultAuthorIcon.jpg"/>
                        </c:when>
                        <c:otherwise>
                            <c:url var="authorIconImageUrl"
                                   value="/dimage/${author.iconImageId}"/>
                        </c:otherwise>
                    </c:choose>

                    <a class="author-icon" >
                        <img src="${authorIconImageUrl}">
                    </a>


                    <div class="author-detail-content">
                        <header class="author-nick-name">
                            <h1><c:out escapeXml="true" value="${author.nickName}" /></h1>
                        </header>
                        <footer>
                            <fmt:message key="jsp.author.label.totalFollowup">
                                <fmt:param value="${additionalInfo.followedByNumber}"/>
                            </fmt:message>
                        </footer>
                    </div>
                    <div class="author-actions-wrapper">
                        <c:choose >
                            <c:when test="${isAuthenticatedAuthor}">
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
                        page="/anthology/authorAnthologySummariesCollection/${author.id}"/>

                <c:url var="authorAnthologySummariesCollectionUrl"
                       value="/anthology/authorAnthologySummariesCollection/${author.id}"/>
                <a href="${authorAnthologySummariesCollectionUrl}"
                   class="load-more-btn">
                    <fmt:message key="jsp.common.link.loadMore"/>
                </a>
            </section>
            <aside>
                <article>
                    <header>
                        <fmt:message key="jsp.author.label.authorDescription"/>
                    </header>
                    <section>
                        <c:out value="${author.description}" escapeXml="true"/>
                    </section>
                </article>

                <ul class="followings">
                    <li class="title">
                        <fmt:message key="jsp.author.label.following" />
                    </li>
                    <li class="follower-icons-container">
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>

                    </li>
                </ul>

                <ul class="followers">
                    <li class="title">
                        <fmt:message key="jsp.author.label.follower" />
                    </li>
                    <li class="follower-icons-container">
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>
                        <a href="#" class="follower-icon-wrapper">
                            <c:choose>
                                <c:when test="${anthologyDetail.authorIconImageId == null}">
                                    <c:url var="authorIconImageUrl" value="/image/defaultAuthorIcon.jpg"/>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="authorIconImageUrl"
                                           value="/dimage/${anthologyDetail.authorIconImageId}"/>
                                </c:otherwise>
                            </c:choose>
                            <img src="${authorIconImageUrl}">
                        </a>

                    </li>
                </ul>
            </aside>
        </main>
        <tongwen:footer/>
    </body>
</html>
