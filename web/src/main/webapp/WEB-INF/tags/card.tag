<%@ tag pageEncoding="UTF-8" body-content="scriptless"
        trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="coverImageId" type="java.lang.Long" %>
<%@ attribute name="title" type="java.lang.String" %>
<%@ attribute name="summary" type="java.lang.String" %>
<%@ attribute name="publishDate" type="java.util.Date" %>
<%@ attribute name="viewDetailUrl" type="java.lang.String" %>
<%@ attribute name="viewAuthorUrl" type="java.lang.String" %>
<%@ attribute name="authorIconImageId" type="java.lang.Long" %>
<%@ attribute name="authorNickName" type="java.lang.String" %>
<%@ attribute name="currentAuthorIconImageId" type="java.lang.Long" %>
<%@ attribute name="currentAuthorNickName" type="java.lang.String" %>
<%@ attribute name="commentList" type="java.util.List" %>

<c:url var="defaultAuthorIconImageUrl" value="/image/author.jpg"/>

<div class="card">
    <header>
        <c:set var="cardAuthorIconImageUrl" value="${defaultAuthorIconImageUrl}" />
        <c:if test="${authorIconImageId!=null}">
            <c:url var="cardAuthorIconImageUrl"
                   value="/dimage/${authorIconImageId}"/>
        </c:if>

        <a href="${viewAuthorUrl}" class="author-icon-wrapper">
            <img src="${cardAuthorIconImageUrl}">
        </a>
        <a href="${viewAuthorUrl}" class="author-nick-name">
            <c:out value="${authorNickName}" escapeXml="true"/>
        </a>
        <span class="publish-time">
            <fmt:message key="jsp.article.label.publishOn">
                <fmt:formatDate
                        value="${publishDate}"
                        var="formatCardPublishDate"
                        pattern="yyyy-MM-dd"/>
                <fmt:param value="${formatCardPublishDate}"/>
            </fmt:message>
        </span>
        <span class="card-header-actions-trigger fa fa-ellipsis-v"></span>
    </header>
    <article>
        <header>
            <h1>
                <a href="${viewDetailUrl}">
                    <c:out value="${title}" escapeXml="true"/>
                </a>
            </h1>
        </header>
        <section>
            <p>
                <c:out value="${summary}" escapeXml="true" />
            </p>
        </section>
        <c:if test="${coverImageId !=null}">
            <c:url value="/dimage/${coverImageId}" var="coverImageUrl"/>
            <a href="${viewDetailUrl}" style="background-image: url('${coverImageUrl}')" class="card-cover"></a>
        </c:if>
        <footer>
            <a href="#" class="action left fa fa-heart-o"></a>
            <a href="#" class="action fa fa-commenting-o"></a>
            <a href="#" class="action fa fa-bookmark-o"></a>
        </footer>
    </article>
    <div class="comment-section">
        <ul class="expand">
            <c:forEach var="currentComment" items="${commentList}">
                <li>
                    <c:set var="currentCommenterIconImageUrl" value="${defaultAuthorIconImageUrl}" />
                    <c:if test="${currentComment.commenterIconImageId!=null}">
                        <c:url var="currentCommenterIconImageUrl"
                               value="/dimage/${currentComment.commenterIconImageId}"/>
                    </c:if>
                    <c:url var="currentCommenterUrl" value="/author/${currentComment.commenterId}/view"/>
                    <a href="${currentCommenterUrl}" class="commenter-icon-wrapper">
                        <img src="${currentCommenterIconImageUrl}">
                    </a>
                    <article>
                        <a href="${currentCommenterIconImageUrl}" class="commenter-nick-name">
                            <c:out value="${currentComment.commenterNickName}" escapeXml="true"/>
                        </a>
                        <section>
                            <c:out value="${currentComment.text}" escapeXml="true"/>
                        </section>
                    </article>
                </li>
            </c:forEach>
        </ul>
        <div class="comment-editor">
            <c:set var="currentAuthorIconImageUrl" value="${defaultAuthorIconImageUrl}" />
            <c:if test="${currentAuthorIconImageId!=null}">
                <c:url var="currentAuthorIconImageUrl"
                       value="/dimage/${currentAuthorIconImageId}"/>
            </c:if>
            <a href="#" class="commenter-icon-wrapper">
                <img src="${currentAuthorIconImageUrl}">
            </a>
            <div class="comment-editor-operation-panel">
                <fmt:message key="tag.card.link.comment.editor.placeHolder" var="commentEditorPlaceholder"/>
                <textarea title="" placeholder="${commentEditorPlaceholder}"></textarea>
                <a href="#" class="">
                    <fmt:message key="tag.card.link.comment.editor.btn.publish"/>
                </a>
            </div>
        </div>
    </div>
</div>
