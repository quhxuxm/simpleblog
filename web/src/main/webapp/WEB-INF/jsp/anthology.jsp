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
            <section class="anthology-summary">
                <c:if test="${anthologyDetail.coverImageId != null}">
                    <a class="anthology-cover"></a>
                </c:if>

                <div class="anthology-content">
                    <header class="anthology-title">
                        <h1><c:out escapeXml="true" value="${anthologyDetail.title}" /></h1>
                    </header>
                    <section>
                        <c:out escapeXml="true" value="${anthologyDetail.summary}" />
                    </section>
                    <footer>
                        <fmt:message key="jsp.anthology.label.totalBookmarkNumber">
                            <fmt:param value="${anthologyAdditionalInfo.bookmarkNumber}"/>
                        </fmt:message>
                    </footer>
                </div>
                <div class="anthology-actions-wrapper">
                </div>
            </section>
            <section class="article-summaries">
                <jsp:include
                        page="/article/anthologyArticleSummariesCollection/${anthologyDetail.id}"/>
                <c:url var="anthologyArticleSummariesCollection"
                       value="/article/anthologyArticleSummariesCollection/${anthologyDetail.id}"/>
                <a href="${anthologyArticleSummariesCollection}"
                   class="load-more-article-btn">
                    <fmt:message key="jsp.common.link.loadMore"/>
                </a>
            </section>
        </main>
        <tongwen:footer/>
    </body>
</html>
