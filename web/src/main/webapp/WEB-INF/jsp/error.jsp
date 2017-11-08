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
            <c:url value="/css/error.css" var="errorCssUrl"/>
            <link href="${errorCssUrl}" type="text/css" rel="stylesheet"/>
        </jsp:attribute>
    </tongwen:head>
    <body>
        <tongwen:nav />
        <main class="error">
            <div class="message-box error">
                <fmt:message key="jsp.error.${param.get('code')}"/>
            </div>
        </main>
        <tongwen:footer/>
    </body>
</html>
