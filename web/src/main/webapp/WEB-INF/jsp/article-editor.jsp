<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <tongwen:head>
    <jsp:attribute name="additional">
        <c:url value="/css/article-editor.css" var="articleEditorCssUrl"/>
    <link href="${articleEditorCssUrl}" type="text/css" rel="stylesheet"/>
        <c:url value="/css/article-content.css" var="articleContentCssUrl"/>
    <link href="${articleContentCssUrl}" type="text/css" rel="stylesheet"/>
        <c:url value="/js/article-editor.js" var="articleEditorJsUrl"/>
    <script type="application/javascript" src="${articleEditorJsUrl}"></script>
    </jsp:attribute>
    </tongwen:head>
    <body>
        <main>
            <c:url var="articleEditActionsBaseUrl" value="/article/"/>
            <section class="article article-editor" data-url="${articleEditActionsBaseUrl}" data-articleId="${article.id}" data-csrfHeader="${_csrf.headerName}" data-csrfToken="${_csrf.token}">
                <ul class="toolbar">
                    <li class="toolbar-btn-wrapper save-btn-wrapper">
                        <a href="#" class="action save">
                            <span class="fa fa-save"></span>
                        </a>
                    </li>
                    <li class="toolbar-btn-wrapper publish-btn-wrapper">
                        <a href="#" class="action publish">
                            <span class="fa fa-share"></span>
                        </a>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#">
                            <fmt:message key="jsp.articleEditor.toolbar.btn.label.h1"/>
                        </a>
                        <span class="fa fa-sort-down toolbar-btn-icon-subscript"></span>
                        <ul class="toolbar-btn-actions-menu">
                            <li>
                                <a href="#" class="action h1">
                                    <fmt:message key="jsp.articleEditor.toolbar.btn.label.h1"/>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="action h2">
                                    <fmt:message key="jsp.articleEditor.toolbar.btn.label.h2"/>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="action h3">
                                    <fmt:message key="jsp.articleEditor.toolbar.btn.label.h3"/>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="action h4">
                                    <fmt:message key="jsp.articleEditor.toolbar.btn.label.h4"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#">
                            <span class="fa fa-bold"></span>
                        </a>
                        <span class="fa fa-sort-down toolbar-btn-icon-subscript"></span>
                        <ul class="toolbar-btn-actions-menu">
                            <li>
                                <a href="#" class="action bold">
                                    <span class="fa fa-bold"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="action italic">
                                    <span class="fa fa-italic"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="action strikethrough">
                                    <span class="fa fa-strikethrough"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#" class="action p">
                            <span class="fa fa-paragraph"></span>
                        </a>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#" class="action BLOCKQUOTE">
                            <span class="fa fa-quote-left"></span>
                        </a>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#" class="action image">
                            <span class="fa fa-image"></span>
                        </a>
                        <input type="file" multiple accept="image/*"/>
                    </li>
                    <li class="toolbar-btn-wrapper">
                        <a href="#" class="action undo">
                            <span class="fa fa-undo"></span>
                        </a>
                    </li>
                </ul>
                <fmt:message key="jsp.articleEditor.titleBox.placeholder" var="titleBoxPlaceholder"/>
                <input class="title-box" type="text" placeholder="${titleBoxPlaceholder}" value="${article.title}"/>
                <article>
                    <fmt:message key="jsp.articleEditor.contentBox.placeholder" var="contentBoxPlaceholder"/>
                    <section class="content-box" contenteditable="true" data-placeholder="${contentBoxPlaceholder}">${article.content}</section>
                </article>
            </section>
        </main>
        <div class="additional-actions-wrapper">
            <span class="fa fa-cog trigger"></span>
            <ul class="additional-actions-menu">
                <li>
                    <c:url value="/" var="indexPageUrl"/>
                    <a href="${indexPageUrl}" class="additional-action-btn">
                        <span class="additional-action-btn-label">
                            <fmt:message key="jsp.articleEditor.additionalActionsMenu.item.returnToMainPage"/>
                        </span>
                        <span class="fa fa-reply additional-action-btn-icon"></span>
                    </a>
                </li>
                <li>
                    <a href="#" class="additional-action-btn">
                        <span class="additional-action-btn-label">
                            <fmt:message key="jsp.articleEditor.additionalActionsMenu.item.selectAnthology"/>
                        </span>
                        <span class="fa fa-folder-open additional-action-btn-icon"></span>
                    </a>
                    <ul class="additional-actions-menu anthology-menu">
                        <c:forEach var="anthologySummary" items="${anthologies}">
                            <li>
                                <a href="#" class="additional-action-btn anthology" data-anthology-id="${anthologySummary.id}">
                                    <span class="additional-action-btn-label">
                                        <c:if test="${(article==null && defaultAnthologyId eq anthologySummary.id) || (article !=null && (article.anthologyId eq anthologySummary.id))}">
                                            <c:set var="checkStatus" value="checked"/>
                                        </c:if>
                                        <span class="fa fa-check-circle anthology-check-status ${checkStatus}"></span>
                                        <c:remove var="checkStatus"/>
                                        ${anthologySummary.title}
                                    </span>
                                    <span class="fa fa-book additional-action-btn-icon"></span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="image-uploader-wrapper">
            <div class="image-uploader">
                <div class="image-uploader-progressbar"></div>
            </div>
        </div>
        <div class="overlay">
        </div>
        <div class="message-box-wrapper">
            <div class="message-box success">
                <ul>
                    <li class="title">
                        <span class="fa fa-check"></span><fmt:message key="jsp.articleEditor.responseMessage.saveSuccess.title"/>
                    </li>
                </ul>
            </div>
            <div class="message-box error">
                <ul>
                    <li class="title">
                        <span class="fa fa-close"></span><fmt:message key="jsp.articleEditor.responseMessage.saveError.title"/>
                    </li>
                    <li class="errorMessage SYSTEM_ERROR">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.systemError"/>
                    </li>
                    <li class="errorMessage TITLE_IS_EMPTY">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.titleIsEmpty"/>
                    </li>
                    <li class="errorMessage TITLE_TOO_LONG">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.titleTooLong"/>
                    </li>
                    <li class="errorMessage TITLE_TOO_SHORT">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.titleTooShort"/>
                    </li>
                    <li class="errorMessage CONTENT_IS_EMPTY">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.contentIsEmpty"/>
                    </li>
                    <li class="errorMessage CONTENT_TOO_LONG">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.contentTooLong"/>
                    </li>
                    <li class="errorMessage CONTENT_TOO_SHORT">
                        <fmt:message key="jsp.articleEditor.responseMessage.saveError.body.contentTooShort"/>
                    </li>
                </ul>
            </div>
        </div>
    </body>
