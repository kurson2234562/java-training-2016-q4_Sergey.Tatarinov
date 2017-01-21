<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="x" uri="/WEB-INF/student_journal.tld" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sql:setDataSource var="db" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/facultaty" user="admin" password="admin"/>
<c:choose>
    <c:when test="${not empty mark}">
        <sql:query dataSource="${db}" var="result">
            SELECT surname, name, patronymic
            FROM STUDENT_COURSE
            INNER JOIN STUDENTS ON STUDENTS.ID=STUDENT_COURSE.ID_STUDENT
            WHERE STUDENT_COURSE.ID_STUDENT_COURSE=${id_student_course}
        </sql:query>
    </c:when>
    <c:otherwise>
        <sql:query dataSource="${db}" var="result">
            SELECT surname, name, patronymic, mark
            FROM JOURNAL
            INNER JOIN STUDENT_COURSE ON STUDENT_COURSE.id_student_course = JOURNAL.id_student_course
            INNER JOIN STUDENTS ON STUDENTS.ID=STUDENT_COURSE.ID_STUDENT
            WHERE JOURNAL.ID_STUDENT_COURSE=${id_student_course}
        </sql:query>
    </c:otherwise>
</c:choose>
<html>
<head>
    <title> <my:Locale value="page.change.mark.title"/> </title>

    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerbackup.jspf"%>
<div id="content">
    <%@ include file="/WEB-INF/jspf/about.jspf"%>
    <div class="list">
        <p><my:Locale value="page.change.mark.title"/></p>
        <c:choose>
            <c:when test="${not empty result.rows}">
                <table>
                    <tr>
                        <c:forEach items="${result.rows}" var="row" >
                            <c:choose>
                                <c:when test="${not empty row.patronymic}">
                                    <th colspan="3">
                                        <my:Locale value="page.lecturer.student"/>
                                    </th>
                                </c:when>
                                <c:otherwise>
                                    <th colspan="2">
                                        <my:Locale value="page.lecturer.student"/>
                                    </th>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <th><my:Locale value="page.student.mark"/></th>
                    </tr>
                    <c:forEach items="${result.rows}" var="row" >
                        <tr>
                            <td>${row.surname}</td>
                            <td>${row.name}</td>
                            <c:choose>
                                <c:when test="${not empty row.patronymic}">
                                    <td>${row.patronymic}</td>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${not empty row.mark}">
                                    <td>${row.mark}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>—</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h3><my:Locale value="page.courses.error"/></h3>
            </c:otherwise>
        </c:choose>
        <form>
            <c:choose>
                <c:when test="${not empty mark}">
                    <input type="hidden" name="command" value="insertInJournalCommand">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="command" value="updateJournalCommand">
                </c:otherwise>
            </c:choose>
            <my:Locale value="page.lecturer.journal.hint"/>
            <input type="number" name="newValue" min="0" max="100" required>
            <input type="submit" value="<my:Locale value="page.change.mark"/>">
        </form>
    </div>
</div>
</body>
</html>