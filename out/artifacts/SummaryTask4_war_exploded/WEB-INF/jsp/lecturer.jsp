<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld" %>
<%@ taglib prefix="a" uri="/WEB-INF/journal.tld" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/courses?encoding=UTF-8&amp;useUnicode=true&amp;characterEncoding=UTF-8" user="root" password="remdigga4237"/>

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
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title> <my:Locale value="page.lecturer.title"/> </title>
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <script src="../../bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <link rel="stylesheet" type="text/css" href="../../styles/lecturer.css">
        <div class="container-fluid bs-const">
            <div class="col-lg-3">
                <%@ include file="/WEB-INF/jspf/about.jspf"%>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="loadInformationCommand">
                    <button type="submit" class="btn btn-success profile">Редактировать личную информацию</button>
                </form>
                <div class="bs-example" data-example-id="simple-nav-stacked">
                    <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
                        <li role="presentation">
                            <a href="#ratings"><my:Locale value="page.lecturer.table.title"/></a>
                        </li>
                        <c:if test="${not empty result.rows}">
                            <li role="presentation">
                                <a href="#evaluate"><my:Locale value="page.lecturer.leftbar.notevaluatedcourses"/></a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <%@ include file="/WEB-INF/jspf/endabout.jspf"%>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <a name="ratings"></a>
                    <div class="panel panel-primary table-responsive">
                        <div class="panel-heading"><my:Locale value="page.lecturer.table.title"/></div>
                        <a:journal/>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${not empty result.rows}">
                        <div class="row">
                            <a name="evaluate"></a>
                            <div class="panel panel-primary">
                                <div class="panel-heading"><my:Locale value="page.lecturer.student.notmark"/></div>
                                <div class="table-responsive">
                                    <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><my:Locale value="page.lecturer.alert.evaluation"/> </div>
                                    <table class="table table-bordered table-striped">
                                        <tr>
                                            <th class="info"><my:Locale value="page.people.course.name"/> </th>
                                            <c:forEach items="${result.rows}" var="row" >
                                                <c:set value="${row.patronymic}" var="patronymic"/>
                                            </c:forEach>
                                            <c:choose>
                                                <c:when test="${not empty patronymic}">
                                                    <th class="info" colspan="3">
                                                        <my:Locale value="page.lecturer.student"/>
                                                    </th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th class="info" colspan="2">
                                                        <my:Locale value="page.lecturer.student"/>
                                                    </th>
                                                </c:otherwise>
                                            </c:choose>
                                            <th class="info"><my:Locale value="page.people.course.actions"/> </th>
                                        </tr>
                                        <c:forEach items="${result.rows}" var="row" >
                                            <c:set value="${row.id_user}" scope="session" var="id_student_course"/>
                                            <tr>
                                                <form method="post">
                                                    <input type="hidden" name="command" value="selectStudentCommand">
                                                    <input type="hidden" name="id" value="${row.id_student_course}">
                                                    <input type="hidden" name="mark" value="new">
                                                    <td>${row.name_course}</td>
                                                    <td>${row.surname}</td><td>${row.name}</td>
                                                    <c:if test="${not empty patronymic}">
                                                        <td>${row.patronymic}</td>
                                                    </c:if>
                                                    <td><button type="submit" class="btn btn-success"><my:Locale value="page.lecturer.estimate"/></button> </td>
                                                </form>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </body>
</html>
