<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<c:forEach var="articleSummary" items="${summariesCollection}">
    <tongwen:card articleSummary="${articleSummary}"
                  articleAdditionalInfo="${additionalInfoMap.get(articleSummary.id)}"/>
</c:forEach>
<input type="hidden" name="nextStart" value="${nextStart}"/>
