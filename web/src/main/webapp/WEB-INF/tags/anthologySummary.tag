<%@ tag pageEncoding="UTF-8" body-content="scriptless"
        trimDirectiveWhitespaces="true" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="anthologySummary" type="com.tongwen.domain.AnthologySummary" %>
<%@ attribute name="anthologyAdditionalInfo"
              type="com.tongwen.domain.AnthologyAdditionalInfo" %>

<c:url var="viewAnthologyUrl" value="/anthology/${anthologySummary.id}/view"/>
<c:url var="viewAuthorUrl" value="/author/${anthologySummary.authorId}/view"/>

<c:choose>
    <c:when test="${sessionScope.authenticatedAuthor != null && sessionScope.authenticatedAuthor.id eq anthologySummary.authorId}">
        <c:set value="${true}" var="isOwner" />
    </c:when>
    <c:otherwise>
        <c:set value="${false}" var="isOwner" />
    </c:otherwise>
</c:choose>
<article>
    <c:if test="${anthologySummary.coverImageId !=null}">
        <c:url value="/dimage/${anthologySummary.coverImageId}"
               var="anthologyCoverUrl"/>
        <a href="${viewAnthologyUrl}"
           style="background-image: url('${anthologyCoverUrl}')"
           class="anthology-cover"></a>
    </c:if>
    <div class="anthology-content">
        <header>
            <a href="${viewAnthologyUrl}" class="anthology-title">
                <c:out value="${anthologySummary.title}" escapeXml="true"/>
            </a>
            <div class="anthology-author-wrapper">
                <a href="${viewAuthorUrl}" class="icon-img-wrapper">
                    <c:choose>
                        <c:when test="${anthologySummary.authorIconImageId ==null}">
                            <c:url var="authorIconImageUrl"
                                   value="/image/defaultAuthorIcon.jpg"/>
                        </c:when>
                        <c:otherwise>
                            <c:url var="authorIconImageUrl"
                                   value="/dimage/${anthologySummary.authorIconImageId}"/>
                        </c:otherwise>
                    </c:choose>
                    <img src="${authorIconImageUrl}">
                </a>
                <div class="author-nick-name-wrapper">
                    <a href="${viewAuthorUrl}"
                       class="author-nick-name"> ${anthologySummary.authorNickName} </a>
                    <span class="anthology-publish-timestamp">
                        <fmt:message key="jsp.article.label.publishOn">
                            <fmt:formatDate
                                    value="${anthologySummary.publishDate}"
                                    var="anthologyPublishDate"
                                    pattern="yyyy-MM-dd"/>
                            <fmt:param value="${anthologyPublishDate}"/>
                        </fmt:message>
                    </span>
                </div>
                <c:url var="followupAuthorUrl"
                       value="/author/${anthologySummary.authorId}/fullowup"/>
                <a href="${followupAuthorUrl}" class="icon-btn">
                    <span class="fa fa-plus-circle"></span>
                    <fmt:message key="jsp.common.link.followup"/>
                </a>
            </div>
        </header>
        <section>
            <c:out value="${anthologySummary.summary}" escapeXml="true"/>
        </section>
        <footer>
            <c:choose>
                <c:when test="${isOwner}">

                    <c:url value="/anthology/${anthologySummary.id}/followup"
                           var="followupAnthologyUrl"/>
                    <a href="${followupAnthologyUrl}" class="action bookmark">
                        <span class="fa fa-bookmark"></span>${anthologyAdditionalInfo.bookmarkNumber}
                    </a>

                    <c:url value="/anthology/${anthologySummary.id}/remove"
                           var="removeArticleUrl"/>
                    <a href="${removeArticleUrl}" class="action remove">
                        <span class="fa fa-trash"></span>
                    </a>
                    <c:url value="/anthology/${anthologySummary.id}/update"
                           var="updateArticleUrl"/>
                    <a href="${updateArticleUrl}" class="action update">
                        <span class="fa fa-pencil"></span>
                    </a>
                </c:when>
                <c:otherwise>

                    <c:url value="/anthology/${anthologySummary.id}/followup"
                           var="followupAnthologyUrl"/>
                    <a href="${followupAnthologyUrl}" class="action bookmark">
                        <span class="fa fa-bookmark"></span>${anthologyAdditionalInfo.bookmarkNumber}
                    </a>
                </c:otherwise>
            </c:choose>

        </footer>
    </div>
</article>
