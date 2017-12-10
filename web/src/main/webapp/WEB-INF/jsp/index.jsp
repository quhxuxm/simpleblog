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
            <c:url value="/js/article.js" var="articleJsUrl" />
            <script type="application/javascript" src="${articleJsUrl}"></script>
            <c:url value="/css/index.css" var="indexCssUrl"/>
            <link href="${indexCssUrl}" type="text/css" rel="stylesheet"/>
            <c:url value="/js/card.js" var="cardJsUrl" />
            <script type="application/javascript" src="${cardJsUrl}"></script>
        </jsp:attribute>
    </tongwen:head>
    <body id="index">
        <tongwen:nav />
        <main>
            <jsp:include page="/article/allPublishedArticleSummariesCollection"/>
            <c:url var="allPublishedArticleSummariesCollection" value="/article/allPublishedArticleSummariesCollection"/>
        </main>
    </body>
</html>
