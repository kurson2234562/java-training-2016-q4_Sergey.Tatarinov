<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="v" uri="/WEB-INF/courses.tld"%>
<%@ taglib prefix="b" uri="/WEB-INF/db.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:setDataSource var="db" driver="org.apache.derby.jdbc.ClientDriver" url="jdbc:derby://localhost:1527/facultaty" user="admin" password="admin"/>

<c:choose>
    <c:when test="${not empty sort}">
        <c:choose>
            <c:when test="${not empty idTheme and not empty idLecturer}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE ID = ${idLecturer} AND Courses.id_theme = ${idTheme} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME ORDER BY ${sort} ${sorting} </sql:query>
            </c:when>
            <c:when test="${not empty idLecturer}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE ID = ${idLecturer} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME ORDER BY ${sort} ${sorting} </sql:query>
            </c:when>
            <c:when test="${not empty idTheme}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE COURSES.id_theme = ${idTheme} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME ORDER BY ${sort} ${sorting} </sql:query>
            </c:when>
            <c:otherwise>
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME ORDER BY ${sort} ${sorting} </sql:query>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not empty idTheme and not empty idLecturer}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE ID = ${idLecturer} AND Courses.id_theme = ${idTheme} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME</sql:query>
            </c:when>
            <c:when test="${not empty idLecturer}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE ID = ${idLecturer} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME </sql:query>
            </c:when>
            <c:when test="${not empty idTheme}">
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course WHERE COURSES.id_theme = ${idTheme} GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME</sql:query>
            </c:when>
            <c:otherwise>
                <sql:query dataSource="${db}" var="result"> SELECT COURSES.ID_THEME, NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, count(id_student_course) as COUNT FROM COURSES INNER JOIN THEMES ON THEMES.ID_THEME=COURSES.ID_THEME INNER JOIN LECTURERS ON LECTURERS.ID=COURSES.ID_LECTURER  INNER JOIN STATUSES ON STATUSES.ID_STATUS=COURSES.ID_STATUS INNER JOIN STUDENT_COURSE ON COURSES.id_course = STUDENT_COURSE.id_course GROUP BY NAME_COURSE, DURATION, NAME_THEME, SURNAME, NAME, PATRONYMIC, NAME_STATUS, COURSES.ID_THEME </sql:query>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<html>

    <head>
        <title> <my:Locale value="page.courses.title"/> </title>
        <link rel="stylesheet" type="text/css" href="../../styles/courses.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="content">
            <%@ include file="/WEB-INF/jspf/about.jspf"%>
            <div class="list">
                <p><my:Locale value="page.courses.title"/> </p>
                <form method="get">
                    <input type="hidden" name="command" value="courseCommand">
                    <select name ="idTheme">
                        <option selected><my:Locale value="page.courses.all.themes"/></option>
                        <c:forEach items="${themes}" var="theme">
                            <option value="${theme.idTheme}"
                            <c:choose>
                                <c:when test="${not empty idTheme and idTheme==theme.idTheme}">
                                    selected
                                </c:when>
                            </c:choose>
                            >${theme.nameTheme}</option>>
                        </c:forEach>
                    </select>
                    <select name ="idLecturer">
                        <option selected><my:Locale value="page.courses.all.lecturers"/></option>
                        <c:forEach items="${lecturers}" var="lecturer">
                            <option value="${lecturer.id}"
                                <c:choose>
                                    <c:when test="${not empty idLecturer and idLecturer==lecturer.id}">
                                        selected
                                    </c:when>
                                </c:choose>
                            >${lecturer.surname} ${lecturer.name} ${lecturer.patronymic}</option>>
                        </c:forEach>
                    </select>
                    <input type="submit" value="<my:Locale value="page.courses.choose"/> " id="sort">
                    <c:choose>
                        <c:when test="${not empty result.rows}">
                            <table>
                                <tr>
                                    <th><button name="sort" value="name_course"><my:Locale value="page.people.course.name"/></button></th>
                                    <th><button name="sort" value="duration"><my:Locale value="page.people.course.duration"/></button></th>
                                    <th><button name="sort" value="name_theme"><my:Locale value="page.student.theme"/></button></th>
                                    <th colspan="3">
                                        <button name="sort" value="surname"><my:Locale value="page.student.lecturer"/></button>
                                    </th>
                                    <th><button name="sort" value="name_status"><my:Locale value="page.courses.table.status"/></button></th>
                                    <th><button name="sort" value="count"><my:Locale value="page.courses.table.count"/></button></th>
                                </tr>
                                <c:forEach items="${result.rows}" var="row" >
                                    <tr>
                                        <td>${row.name_course}</td>
                                        <td>${row.duration}</td>
                                        <td>${row.name_theme}</td>
                                        <td>${row.surname}</td>
                                        <td>${row.name}</td>
                                        <td>${row.patronymic}</td>
                                        <td>${row.name_status}</td>
                                        <td>${row.count}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <h3><my:Locale value="page.courses.error"/></h3>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
            <cr:copyright/>
        </div>
    </body>
</html>
