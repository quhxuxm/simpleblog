<%@ tag pageEncoding="UTF-8" body-content="scriptless"
        trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="articleSummary" type="com.tongwen.domain.ArticleSummary" %>
<%@ attribute name="articleAdditionalInfo"
              type="com.tongwen.domain.ArticleAdditionalInfo" %>
<c:url var="viewArticleUrl" value="/article/${articleSummary.id}/view"/>
<c:url var="viewAuthorUrl" value="/author/${articleSummary.authorId}/view"/>
<article>
    <c:if test="${articleSummary.coverImageId !=null}">
        <c:url value="/dimage/${articleSummary.coverImageId}"
               var="articleCoverUrl"/>
        <a href="${viewArticleUrl}"
           style="background-image: url('${articleCoverUrl}')"
           class="article-cover"></a>
    </c:if>
    <div class="article-content">
        <header>
            <a href="${viewArticleUrl}" class="article-title">
                <c:out value="${articleSummary.title}" escapeXml="true"/>
            </a>
            <div class="article-author-wrapper">
                <a href="${viewAuthorUrl}" class="icon-img-wrapper">
                    <c:choose>
                        <c:when test="${articleSummary.authorIconImageId ==null}">
                            <c:url var="authorIconImageUrl"
                                   value="/image/defaultAuthorIcon.jpg"/>
                        </c:when>
                        <c:otherwise>
                            <c:url var="authorIconImageUrl"
                                   value="/dimage/${articleSummary.authorIconImageId}"/>
                        </c:otherwise>
                    </c:choose>
                    <img src="${authorIconImageUrl}">
                </a>
                <div class="author-nick-name-wrapper">
                    <a href="${viewAuthorUrl}"
                       class="author-nick-name"> ${articleSummary.authorNickName} </a>
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
        </header>
        <section>
            <c:out value="${articleSummary.summary}" escapeXml="true"/>
        </section>
        <footer>
            <c:url var="viewAnthologyUrl"
                   value="/anthology/${articleSummary.anthologyId}/view"/>
            <a href="${viewAnthologyUrl}" class="anthology-name">
                <c:out value="${articleSummary.anthologyTitle}"
                       escapeXml="true"/>
            </a>
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
        </footer>
    </div>
</article>
