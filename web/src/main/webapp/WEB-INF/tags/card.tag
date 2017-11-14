<%@ tag pageEncoding="UTF-8" body-content="scriptless"
        trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="cardCoverImageId" type="java.lang.Long" %>
<%@ attribute name="cardTitle" type="java.lang.String" %>
<%@ attribute name="cardSummary" type="java.lang.String" %>
<%@ attribute name="cardTargetUrl" type="java.lang.String" %>
<%@ attribute name="cardAuthorUrl" type="java.lang.String" %>
<%@ attribute name="cardAuthorIconImageId" type="java.lang.Long" %>
<%@ attribute name="cardAuthorNickName" type="java.lang.String" %>
<article class="card">
    <c:if test="${cardCoverImageId !=null}">
        <c:url value="/dimage/${cardCoverImageId}" var="coverImageUrl"/>
        <a href="${cardTargetUrl}" style="background-image: url('${coverImageUrl}')" class="card-cover"></a>
    </c:if>
    <header>
        <a href="${cardTargetUrl}" class="card-content-title">
            <c:out value="${cardTitle}" escapeXml="true"/>
        </a>
    </header>
    <section>
        <c:out value="${cardSummary}" escapeXml="true"/>
    </section>
    <footer>
        <div class="card-author-wrapper">
            <a href="${cardAuthorUrl}" class="card-author-icon-img-wrapper">
                <c:choose>
                    <c:when test="${cardAuthorIconImageId==null}">
                        <c:url var="cardAuthorIconImageUrl"
                               value="/image/defaultAuthorIcon.jpg"/>
                    </c:when>
                    <c:otherwise>
                        <c:url var="cardAuthorIconImageUrl"
                               value="/dimage/${cardAuthorIconImageId}"/>
                    </c:otherwise>
                </c:choose>
                <img src="${cardAuthorIconImageUrl}">
            </a>
            <div class="card-author-nick-name-wrapper">
                <a href="${cardAuthorUrl}" class="card-author-nick-name"> ${cardAuthorNickName} </a>
                <span class="article-publish-timestamp">
                        <fmt:message key="jsp.article.label.publishOn">
                            <fmt:formatDate
                                    value="${articleSummary.publishDate}"
                                    var="articlePublishDate"
                                    pattern="yyyy-MM-dd"/>
                            <fmt:param value="${articlePublishDate}"/>
                        </fmt:message>
                    </span>
            </div>
            <c:url var="followUpAuthorUrl"
                   value="/author/${articleSummary.authorId}/fullowup"/>
            <a href="${followUpAuthorUrl}" class="icon-btn">
                <span class="fa fa-plus-circle"></span>
                <fmt:message key="jsp.common.link.followup"/>
            </a>
        </div>
        <c:url var="viewAnthologyUrl"
               value="/anthology/${articleSummary.anthologyId}/view"/>
        <a href="${viewAnthologyUrl}" class="anthology-name">
            <c:out value="${articleSummary.anthologyTitle}"
                   escapeXml="true"/>
        </a>
        <c:choose>
            <c:when test="${isOwner}">
                <a href="${viewArticleUrl}" class="action">
                    <span class="fa fa-eye"></span>${articleAdditionalInfo.viewNumber}
                </a>
                <c:url value="/article/${articleSummary.id}/praise"
                       var="praiseArticleUrl"/>
                <a href="${praiseArticleUrl}" class="action praise">
                    <span class="fa fa-heart"></span>${articleAdditionalInfo.praiseNumber}
                </a>
                <a href="${viewArticleUrl}" class="action">
                    <span class="fa fa-comment"></span>${articleAdditionalInfo.commentNumber}
                </a>
                <c:url value="/article/${articleSummary.id}/bookmark"
                       var="bookmarkArticleUrl"/>
                <a href="${bookmarkArticleUrl}" class="action bookmark">
                    <span class="fa fa-bookmark"></span>${articleAdditionalInfo.bookmarkNumber}
                </a>
                <c:url value="/article/${articleSummary.id}/remove"
                       var="removeArticleUrl"/>
                <a href="${removeArticleUrl}" class="action remove">
                    <span class="fa fa-trash"></span>
                </a>
                <c:url value="/article/${articleSummary.id}/update"
                       var="updateArticleUrl"/>
                <a href="${updateArticleUrl}" class="action update">
                    <span class="fa fa-pencil"></span>
                </a>
            </c:when>
            <c:otherwise>
                <a href="${viewArticleUrl}" class="action">
                    <span class="fa fa-eye"></span>${articleAdditionalInfo.viewNumber}
                </a>
                <c:url value="/article/${articleSummary.id}/praise"
                       var="praiseArticleUrl"/>
                <a href="${praiseArticleUrl}" class="action praise">
                    <span class="fa fa-heart"></span>${articleAdditionalInfo.praiseNumber}
                </a>
                <a href="${viewArticleUrl}" class="action">
                    <span class="fa fa-comment"></span>${articleAdditionalInfo.commentNumber}
                </a>
                <c:url value="/article/${articleSummary.id}/bookmark"
                       var="bookmarkArticleUrl"/>
                <a href="${bookmarkArticleUrl}" class="action bookmark">
                    <span class="fa fa-bookmark"></span>${articleAdditionalInfo.bookmarkNumber}
                </a>
            </c:otherwise>
        </c:choose>
    </footer>
</article>
