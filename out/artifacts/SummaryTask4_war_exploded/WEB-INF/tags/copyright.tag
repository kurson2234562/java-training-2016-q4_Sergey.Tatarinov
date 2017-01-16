<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" scope="application" class="java.util.Date" />
(${now.hours}:${now.minutes}:${now.seconds}) Copyright &copy; ${now.year + 1900} Epam Java Training