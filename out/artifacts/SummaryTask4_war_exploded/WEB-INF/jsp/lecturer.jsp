<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld" %>
<%@ taglib prefix="a" uri="/WEB-INF/journal.tld" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sql:setDataSource var="db" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/facultaty" user="admin" password="admin"/>

<sql:query dataSource="${db}" var="result">
    SELECT USERS.id_user, STUDENT_COURSE.id_student_course, COURSES.name_course, STUDENTS.surname, STUDENTS.name, STUDENTS.PATRONYMIC, STUDENTS.id_user
    FROM STUDENT_COURSE
    INNER JOIN COURSES ON STUDENT_COURSE.id_course = COURSES.id_course
    INNER JOIN STATUSES ON COURSES.id_status = STATUSES.id_status
    INNER JOIN LECTURERS ON COURSES.id_lecturer = LECTURERS.id
    INNER JOIN USERS ON LECTURERS.id_user = USERS.id_user
    INNER JOIN STUDENTS ON STUDENT_COURSE.id_student = STUDENTS.id
    WHERE NOT EXISTS(
    SELECT JOURNAL.id_student_course
    FROM JOURNAL
    WHERE JOURNAL.id_student_course = STUDENT_COURSE.id_student_course
    ) AND COURSES.id_status=4 AND USERS.id_user = ${user.idUser}


</sql:query>
<html>
    <head>
        <title> <my:Locale value="page.lecturer.title"/> </title>
        <link rel="stylesheet" type="text/css" href="../../styles/lecturer.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="content">
            <%@ include file="/WEB-INF/jspf/about.jspf"%>
            <div class="list">
                <p><my:Locale value="page.lecturer.table.title"/> </p>
                <a:journal/>
            </div>
            <c:choose>
                <c:when test="${not empty result.rows}">
                    <div class="list">
                        <p><my:Locale value="page.lecturer.student.notmark"/> </p>
                        <table>
                            <tr>
                                <th><my:Locale value="page.people.course.name"/> </th>
                                <c:forEach items="${result.rows}" var="row" >
                                    <c:set value="${row.patronymic}" var="patronymic"/>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${not empty patronymic}">
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
                                <th><my:Locale value="page.people.course.actions"/> </th>
                            </tr>
                            <c:forEach items="${result.rows}" var="row" >
                                <c:set value="${row.id_user}" scope="session" var="id_student_course"/>
                                <tr>
                                    <form>
                                        <input type="hidden" name="command" value="selectStudentCommand">
                                        <input type="hidden" name="id" value="${row.id_student_course}">
                                        <input type="hidden" name="mark" value="new">
                                        <td>${row.name_course}</td>
                                        <td>${row.surname}</td><td>${row.name}</td>
                                        <c:if test="${not empty patronymic}">
                                            <td>${row.patronymic}</td>
                                        </c:if>
                                        <td><input type="submit" value="<my:Locale value="page.lecturer.estimate"/> "/></td>
                                    </form>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>
