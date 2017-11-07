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
            <c:url value="/css/anthology.css" var="anthologyCssUrl"/>
            <link href="${anthologyCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav />
        <main>
            <section class="article-summaries">
                <jsp:include page="/article/summariesCollection"/>
                <c:url var="articleSummariesCollectionUrl" value="/article/summariesCollection"/>
                <a href="${articleSummariesCollectionUrl}" class="load-more-article-btn">
                    <fmt:message key="jsp.index.link.loadMore"/>
                </a>
            </section>
        </main>
        <tongwen:footer/>
    </body>
</html>
