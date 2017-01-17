<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="lock" uri="/WEB-INF/lock.tld" %>
<%@ taglib prefix="course" uri="/WEB-INF/editcourse.tld" %>
<%@ taglib prefix="select" uri="/WEB-INF/selectcourse.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title> <my:Locale value="page.admin.title"/> </title>
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="../../styles/admin.css">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="content">
            <%@ include file="/WEB-INF/jspf/about.jspf"%>
            <div class="list">
                <p><my:Locale value="page.admin.student.lock"/> </p>
                <lock:lock/>
            </div>
            <div class="course">
                <form action="controller">
                    <p><my:Locale value="page.admin.update.course"/></p>
                    <input type="hidden" name="command" value="updateCourseButtonCommand">
                    <select:selectcourse/>
                    <input type="submit" value="<my:Locale value="page.admin.update.course"/>">
                </form>
            </div>
            <div class="course">
                <form action="controller">
                    <p><my:Locale value="page.admin.delete.course"/></p>
                    <input type="hidden" name="command" value="deleteCourseCommand">
                    <select:selectcourse/>
                    <input type="submit" value="<my:Locale value="page.admin.delete.course"/>">
                </form>
            </div>
            <div class="list">
                <form>
                    <input type="hidden" name="command" value="createCourseCommand">
                    <p><my:Locale value="page.admin.create.addcourses"/></p>
                    <table>
                        <tr>
                            <th><my:Locale value="page.admin.create.namecourse"/> </th>
                            <th><my:Locale value="page.admin.create.duration"/> </th>
                            <th><my:Locale value="page.admin.create.lecturer"/> </th>
                            <th><my:Locale value="page.admin.create.nametheme"/> </th>
                            <th><my:Locale value="page.admin.create.namestatus"/> </th>
                        </tr>
                        <tr>
                            <course:editCourse method="create"/>
                            <td><input type="submit" value="<my:Locale value="page.admin.create.addcourses"/>"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="list">
                <form action="controller">
                    <input type="hidden" name="command" value="createLecturerCommand">
                    <p><my:Locale value="page.admin.lecturer.create.title"/></p>
                    <table id="newlect">
                        <tr>
                            <td><my:Locale value="page.people.name"/></td>
                            <td><input type="text" name="name" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.people.surname"/></td>
                            <td><input type="text" name="surname" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.people.patronymic"/></td>
                            <td><input type="text" name="patronymic" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.people.login"/></td>
                            <td><input type="text" name="login" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.people.password"/></td>
                            <td><input type="password" name="password" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.people.password.confirm"/></td>
                            <td><input type="password" name="confirm" required/></td>
                        </tr>
                        <tr>
                            <td><my:Locale value="page.admin.pincourse"/></td>
                            <td>
                                <select name="idCourse">
                                <c:forEach items="${sessionScope.courses}" var="course">
                                    <option value="${course.idCourse}">${course.nameCourse}</option>>
                                </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="<my:Locale value="page.admin.lecturer.create.button"/>">
                </form>
            </div>
        </div>
    </body>
</html>
