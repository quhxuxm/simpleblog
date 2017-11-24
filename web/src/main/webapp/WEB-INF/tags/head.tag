<%@ tag pageEncoding="UTF-8" body-content="scriptless" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="additional" fragment="true" required="false"  %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, author-scalable=no"/>
    <title>
        <fmt:message key="jsp.head.title"/>
    </title>
    <c:url var="fontAwesomeCssUrl" value="/css/font-awesome.css"/>
    <link href="${fontAwesomeCssUrl}" type="text/css" rel="stylesheet"/>
    <c:url var="layoutCssUrl" value="/css/layout.css"/>
    <link href="${layoutCssUrl}" type="text/css" rel="stylesheet"/>

    <c:url var="navCssUrl" value="/css/nav.css"/>
    <link href="${navCssUrl}" type="text/css" rel="stylesheet"/>

    <c:url var="footerCssUrl" value="/css/footer.css"/>
    <link href="${footerCssUrl}" type="text/css" rel="stylesheet"/>

    <c:url var="componentCssUrl" value="/css/component.css"/>
    <link href="${componentCssUrl}" type="text/css" rel="stylesheet"/>

    <c:url var="cardCssUrl" value="/css/card.css"/>
    <link href="${cardCssUrl}" type="text/css" rel="stylesheet"/>

    <c:url var="jQueryUrl" value="/js/jquery-3.2.1.js"/>
    <script type="application/javascript" src="${jQueryUrl}"></script>

    <c:url var="frameworkScriptUrl" value="/js/framework.js" />
    <script type="application/javascript" src="${frameworkScriptUrl}"></script>
    <jsp:invoke fragment="additional"  />
</head>
