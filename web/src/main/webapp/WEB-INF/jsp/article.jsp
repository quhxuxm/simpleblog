<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<c:url value="/author/${article.authorId}" var="authorUrl"/>
<c:choose>
    <c:when test="${article.authorIconId!=null}">
        <c:url value="/dimage/${article.authorIconId}" var="authorIconUrl"/>
    </c:when>
    <c:otherwise>
        <c:url value="/image/defaultAuthorIcon.jpg" var="authorIconUrl"/>
    </c:otherwise>
</c:choose>
<html lang="zh_CN">
    <tongwen:head>
    <jsp:attribute name="additional">
        <c:url value="/js/common/article.js" var="articleJsUrl"/>
        <script type="application/javascript" src="${articleJsUrl}"></script>
        <c:url value="/css/article.css" var="articleCssUrl"/>
        <link href="${articleCssUrl}" type="text/css" rel="stylesheet"/>
        <c:url value="/css/article-content.css" var="articleContentCssUrl"/>
        <link href="${articleContentCssUrl}" type="text/css" rel="stylesheet"/>
    </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav/>
        <main>
            <section class="article">
                <article>
                    <header>
                        <h1>${article.title}</h1>
                        <div class="article-author-wrapper">
                            <a href="${authorUrl}" class="icon-img-wrapper">
                                <img src="${authorIconUrl}">
                            </a>
                            <div class="author-nick-name-wrapper">
                                <a href="${authorUrl}" class="author-nick-name">
                                    ${article.authorNickName}
                                </a>
                                <span class="article-publish-timestamp">
                                    <fmt:message key="jsp.article.label.publishOn">
                                        <fmt:formatDate
                                                value="${article.publishDate}"
                                                var="articlePublishDate"
                                                pattern="yyyy-MM-dd"/>
                                        <fmt:param value="${articlePublishDate}"/>
                                    </fmt:message>
                                    </span>
                            </div>
                            <a href="#" class="icon-btn">
                                <span class="fa fa-plus-circle"></span>
                                <fmt:message key="jsp.common.link.followup"/>
                            </a>
                        </div>
                        <div class="article-additional-info-wrapper">
                            <a href="#" class="anthology-name">${article.anthologyTitle}</a>
                            <a href="#" class="action">
                                <fmt:message key="jsp.article.label.read"/>
                                <span class="number">${article.additionalInfo.viewNumber}</span>
                            </a>
                            <a href="#" class="action">
                                <fmt:message key="jsp.article.label.comment"/>
                                <span class="number">${article.additionalInfo.commentNumber}</span>
                            </a>
                            <a href="#" class="action">
                                <fmt:message key="jsp.article.label.bookmark"/>
                                <span class="number">${article.additionalInfo.bookmarkNumber}</span>
                            </a>
                        </div>
                    </header>
                    <section>
                        <p>
                            ${article.content}
                        </p>
                    </section>
                    <footer>
                        <div class="author-detail-wrapper">
                            <section class="summary">
                                <a href="#" class="author-icon-wrapper icon-img-wrapper lg">
                                    <img src="${authorIconUrl}">
                                </a>
                                <div class="author-summary-content-wrapper">
                                    <a href="#" class="author-nick-name">
                                        ${article.authorNickName}
                                    </a>
                                    <div class="author-history-wrapper">
                                        <a href="#">
                                            <fmt:message key="jsp.article.label.publishArticles">
                                                <fmt:param value="${article.authorAdditionalInfo.publishArticleNumber}"/>
                                            </fmt:message>
                                        </a>
                                        <a href="#">
                                            <fmt:message key="jsp.article.label.publishComments">
                                                <fmt:param value="${article.authorAdditionalInfo.publishCommentsNumber}"/>
                                            </fmt:message>
                                        </a>
                                        <a href="#">
                                            <fmt:message key="jsp.article.label.createAnthologies">
                                                <fmt:param value="${article.authorAdditionalInfo.createAnthologiesNumber}"/>
                                            </fmt:message>
                                        </a>
                                        <a href="#">
                                            <fmt:message key="jsp.article.label.followedBy">
                                                <fmt:param value="${article.authorAdditionalInfo.followedByNumber}"/>
                                            </fmt:message>
                                        </a>
                                    </div>
                                </div>
                                <a href="#" class="author-followup-btn icon-btn lg">
                                    <span class="fa fa-plus-circle"></span>
                                    <fmt:message key="jsp.common.link.followup"/>
                                </a>
                            </section>
                            <hr/>
                            <section class="description">
                                ${article.authorDescription}
                            </section>
                        </div>
                        <div class="article-mobile-actions-wrapper">
                            <a href="#" class="author-like-btn icon-btn lg">
                                <span class="fa fa-heart-o"></span>
                                <span class="author-like-btn-label-text">
                                    <fmt:message key="jsp.article.link.up"/>
                                </span>
                                <span class="author-like-btn-label-number">
                                    1000
                                </span>

                            </a>
                        </div>
                    </footer>
                </article>
            </section>
            <div class="comment-publisher-wrapper">
                <textarea placeholder="写下你的评论......"></textarea>
                <div class="comment-publisher-btn-container">
                    <a href="#" class="publish-comment-btn icon-btn lg">
                        <span class="fa fa-send"></span>发布
                    </a>
                </div>
            </div>
            <section class="comments">
                <div class="comment-summary">
                    <span class="comment-number">1000条评论</span>
                </div>
                <aside class="comment">
                    <div class="commenter-wrapper">
                        <a href="#" class="icon-img-wrapper commenter-icon">
                            <img src="../static/image/defaultAuthorIcon.jpg">
                        </a>
                        <div class="commenter-nick-name-wrapper">
                            <a href="#" class="commenter-nick-name">
                                作者1
                            </a>
                            <span class="comment-publish-timestamp">发布于2017/10/10</span>
                        </div>
                    </div>
                    <section>
                        评论内容，评论内容，评论内容，评论内容
                    </section>
                    <footer>
                        <a href="#">
                            <span class="fa fa-heart-o"></span>6人赞
                        </a>
                        <a href="#">
                            <span class="fa fa-comment-o"></span>回复
                        </a>
                    </footer>
                </aside>
                <aside class="comment">
                    <div class="commenter-wrapper">
                        <a href="#" class="icon-img-wrapper commenter-icon">
                            <img src="../static/image/defaultAuthorIcon.jpg">
                        </a>
                        <div class="commenter-nick-name-wrapper">
                            <a href="#" class="commenter-nick-name">
                                作者1
                            </a>
                            <span class="comment-publish-timestamp">发布于2017/10/10</span>
                        </div>
                    </div>
                    <section>
                        评论内容，评论内容，评论内容，评论内容
                    </section>
                    <footer>
                        <a href="#">
                            <span class="fa fa-heart-o"></span>1000人赞
                        </a>
                        <a href="#">
                            <span class="fa fa-comment-o"></span>回复
                        </a>
                    </footer>
                    <ul>
                        <li>
                            <section>
                                评论的回复1
                            </section>
                            <footer>
                                <a href="#" class="commenter-nick-name">
                                    作者1
                                </a>
                                <span class="comment-publish-timestamp">发布于2017/10/10</span>
                                <a href="#" class="reply-btn">
                                    <span class="fa fa-comment-o"></span>回复
                                </a>
                            </footer>
                        </li>
                        <li>
                            <section>
                                评论的回复1
                            </section>
                            <footer>
                                <a href="#" class="commenter-nick-name">
                                    作者1
                                </a>
                                <span class="at">回复</span>
                                <a href="#" class="commenter-nick-name">
                                    作者2
                                </a>
                                <span class="comment-publish-timestamp">发布于2017/10/10</span>
                                <a href="#" class="reply-btn">
                                    <span class="fa fa-comment-o"></span>回复
                                </a>
                            </footer>
                        </li>
                        <li>
                            <section>
                                评论的回复1
                            </section>
                            <footer>
                                <a href="#" class="commenter-nick-name">
                                    作者1
                                </a>
                                <span class="at">回复</span>
                                <a href="#" class="commenter-nick-name">
                                    作者2
                                </a>
                                <span class="comment-publish-timestamp">发布于2017/10/10</span>
                                <a href="#" class="reply-btn">
                                    <span class="fa fa-comment-o"></span>回复
                                </a>
                            </footer>
                        </li>
                        <li>
                            <section>
                                评论的回复1
                            </section>
                            <footer>
                                <a href="#" class="commenter-nick-name">
                                    作者1
                                </a>
                                <span class="comment-publish-timestamp">发布于2017/10/10</span>
                                <a href="#" class="reply-btn">
                                    <span class="fa fa-comment-o"></span>回复
                                </a>
                            </footer>
                        </li>
                        <li>
                            <section>
                                评论的回复1
                            </section>
                            <footer>
                                <a href="#" class="commenter-nick-name">
                                    作者1
                                </a>
                                <span class="comment-publish-timestamp">发布于2017/10/10</span>
                                <a href="#" class="reply-btn">
                                    <span class="fa fa-comment-o"></span>回复
                                </a>
                            </footer>
                        </li>
                        <li>
                            <div class="comment-publisher-wrapper">
                                <textarea placeholder="写下你的评论......"></textarea>
                                <div class="comment-publisher-btn-container">
                                    <a href="#" class="cancel-comment-btn icon-btn lg">
                                        <span class="fa fa-remove"></span>取消
                                    </a>
                                    <a href="#" class="publish-comment-btn icon-btn lg">
                                        <span class="fa fa-send"></span>发布
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </aside>
                <aside class="comment">
                    <div class="commenter-wrapper">
                        <a href="#" class="icon-img-wrapper commenter-icon">
                            <img src="../static/image/defaultAuthorIcon.jpg">
                        </a>
                        <div class="commenter-nick-name-wrapper">
                            <a href="#" class="commenter-nick-name">
                                作者1
                            </a>
                            <span class="comment-publish-timestamp">发布于2017/10/10</span>
                        </div>
                    </div>
                    <section>
                        评论内容，评论内容，评论内容，评论内容
                    </section>
                    <footer>
                        <a href="#">
                            <span class="fa fa-heart-o"></span>1000人赞
                        </a>
                        <a href="#">
                            <span class="fa fa-comment-o"></span>回复
                        </a>
                    </footer>
                    <ul>
                        <li>
                            <div class="comment-publisher-wrapper">
                                <textarea placeholder="写下你的评论......"></textarea>
                                <div class="comment-publisher-btn-container">
                                    <a href="#" class="cancel-comment-btn icon-btn lg">
                                        <span class="fa fa-remove"></span>取消
                                    </a>
                                    <a href="#" class="publish-comment-btn icon-btn lg">
                                        <span class="fa fa-send"></span>发布
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </aside>
            </section>
        </main>
        <footer>
            <ul>
                <li>
                    <a href="#">返回顶部</a>
                </li>
                <li>
                    <a href="#"> 关于我们</a>
                </li>
            </ul>
        </footer>
    </body>
</html>
