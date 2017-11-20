<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tongwen" tagdir="/WEB-INF/tags" %>
<c:forEach var="articleSummary" items="${summariesCollection}">
    <tongwen:card authorIconImageId="${articleSummary.authorIcomImageId}"
                  summary="${articleSummary.summary}"
                  authorNickName="${articleSummary.authorNickName}"
                  publishDate="${articleSummary.publishDate}"
                  coverImageId="${articleSummary.coverImageId}"
                  title="${articleSummary.title}" commentList=""
                  currentAuthorIconImageId="" currentAuthorNickName=""
                  viewAuthorUrl="" viewDetailUrl=""/>
</c:forEach>
<input type="hidden" name="nextStart" value="${nextStart}"/>
