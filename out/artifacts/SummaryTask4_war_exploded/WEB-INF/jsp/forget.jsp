<%--suppress XmlDuplicatedId --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title> <my:Locale value="page.login.title"/> </title>
        <link rel="stylesheet" type="text/css" href="../../styles/forget.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <div id = "flags">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="languageCommand">
                <input type="hidden" name="language" value="en">
                <input type="hidden" name=url value="${requestScope['javax.servlet.forward.query_string']}">
                <input type="image" src="../../img/us.png" alt="Submit">
            </form>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="languageCommand">
                <input type="hidden" name="language" value="ru">
                <input type="hidden" name=url value="${requestScope['javax.servlet.forward.query_string']}">
                <input type="image" src="../../img/ru.png" alt="Submit">
            </form>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="languageCommand">
                <input type="hidden" name="language" value="uk">
                <input type="hidden" name=url value="${requestScope['javax.servlet.forward.query_string']}">
                <input type="image" src="../../img/ua.png" alt="Submit">
            </form>
        </div>
        <div id = "container">
            <img src="../../img/man.png">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="forgetPasswordCommand">
                <div id="form-input">
                    <input type="text" name="email" placeholder="<my:Locale value="page.login.enter"/> email <my:Locale value="page.forger.orlogin"/>" required>
                </div>
                <button type="submit" id="login"> <my:Locale value="page.forger.continue"/></button>
            </form>
        </div>
    </body>
</html>