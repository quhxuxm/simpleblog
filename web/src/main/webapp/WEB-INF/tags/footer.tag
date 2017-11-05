<%@ tag pageEncoding="UTF-8" body-content="scriptless" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="additional" fragment="true" required="false"  %>
<footer>
    <ul>
        <li>
            <a href="#">
                <fmt:message key="jsp.footer.link.returnToTop"/>
            </a>
        </li>
        <li>
            <a href="#">
                <fmt:message key="jsp.footer.link.aboutUs"/>
            </a>
        </li>
    </ul>
</footer>
