<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tables.tld"%>
<%@ taglib prefix="cr" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="pr" uri="/WEB-INF/progress.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title><my:Locale value="page.student.title"/></title>
        <link rel="stylesheet" type="text/css" href="../../styles/logged.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="content">
            <%@ include file="/WEB-INF/jspf/about.jspf"%>
            <div class="list">
                <p><my:Locale value="page.student.table.title.opened"/> </p>
                <t:table status="Opened"/>
            </div>
            <div class="list">
                <p><my:Locale value="page.student.table.title.inprogress"/> </p>
                <t:table status="In progress"/>
            </div>

            <div class="list">
                <p><my:Locale value="page.student.table.title.progress"/> </p>
                <pr:progress/>
            </div>
            <div class="list">
                <p></p>
                <form>
                    <input type="hidden" name="command" value="updateJournalCommand">
                    <select multiple name="cars">
                        <option value="volvo">Volvo</option>
                        <option value="saab">Saab</option>
                        <option value="opel">Opel</option>
                        <option value="audi">Audi</option>
                    </select>
                    <input type="submit">
                </form>
            </div>
        </div>
    </body>
</html>

