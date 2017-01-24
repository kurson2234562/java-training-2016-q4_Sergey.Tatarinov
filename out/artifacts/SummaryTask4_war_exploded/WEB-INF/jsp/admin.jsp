<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="lock" uri="/WEB-INF/lock.tld" %>
<%@ taglib prefix="course" uri="/WEB-INF/editcourse.tld" %>
<%@ taglib prefix="select" uri="/WEB-INF/selectcourse.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><my:Locale value="page.admin.title"/> </title>
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <script src="../../bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <link rel="stylesheet" type="text/css" href="../../styles/admin.css">
        <div class="container-fluid bs-const">
            <div class="col-lg-3">
                <%@ include file="/WEB-INF/jspf/about.jspf"%>
                <div class="bs-example" data-example-id="simple-nav-stacked">
                    <ul class="nav nav-pills nav-stacked nav-pills-stacked-example">
                        <li role="presentation">
                            <a href="#lock"><my:Locale value="page.admin.leftbar.lock"/></a>
                        </li>
                        <li role="presentation">
                            <a href="#course"><my:Locale value="page.admin.leftbar.course"/></a>
                        </li>
                        <li role="presentation">
                            <a href="#add"><my:Locale value="page.admin.leftbar.add"/></a>
                        </li>
                    </ul>
                </div>
                <%@ include file="/WEB-INF/jspf/endabout.jspf"%>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <a name="lock"></a>
                    <div class="panel panel-primary table-responsive">
                        <div class="panel-heading"><my:Locale value="page.admin.student.lock"/> </div>
                        <lock:lock/>
                    </div>
                </div>
                <div class="row">
                    <a name="course"></a>
                    <div class="col-lg-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading" name="<my:Locale value="page.admin.delete.course"/>"><my:Locale value="page.admin.delete.course"/></div>
                            <c:if test="${success}">
                                <p class="bg-success">Курс удалён</p>
                            </c:if>
                            <form method="post" action="controller" class="space">
                                <input type="hidden" name="command" value="deleteCourseCommand">
                                <select:selectcourse/>
                                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-trash"></span><my:Locale value="page.admin.delete.course"/></button>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading"><my:Locale value="page.admin.update.course"/></div>
                            <form method="post" action="controller" class="space">
                                <input type="hidden" name="command" value="updateCourseButtonCommand">
                                <select:selectcourse/>
                                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-pencil"></span><my:Locale value="page.admin.update.course"/></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <a name="add"></a>
                    <div class="col-lg-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading"><my:Locale value="page.admin.create.addcourses"/></div>
                            <form method="post" class="form-horizontal">
                                <course:editCourse method="create"/>
                                <input type="hidden" name="command" value="createCourseCommand">
                                <button type="submit" class="spacebtn btn btn-success"><span class="glyphicon glyphicon-plus"></span><my:Locale value="page.admin.create.addcourses"/></button>
                            </form>

                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading"><my:Locale value="page.admin.lecturer.create.title"/></div>
                            <form method="post" class="form-horizontal">

                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label"><my:Locale value="page.people.name"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="<my:Locale value="page.people.name"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="surname" class="col-sm-2 control-label"><my:Locale value="page.people.surname"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="surname" id="surname" placeholder="<my:Locale value="page.people.surname"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="patronymic" class="col-sm-2 control-label"><my:Locale value="page.people.patronymic"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="patronymic" id="patronymic" placeholder="<my:Locale value="page.people.patronymic"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="login" class="col-sm-2 control-label"><my:Locale value="page.people.login"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" name="login" id="login" class="form-control" placeholder="<my:Locale value="page.people.login"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label"><my:Locale value="page.people.password"/></label>
                                    <div class="col-sm-8">
                                        <input type="password" name="password" id="password" class="form-control" placeholder="<my:Locale value="page.people.password"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="confirm" class="col-sm-2 control-label"><my:Locale value="page.people.password.confirm"/></label>
                                    <div class="col-sm-8">
                                        <input type="password" name="confirm" id="confirm" class="form-control" placeholder="<my:Locale value="page.people.password.confirm"/>" required/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="dropdownMenu" class="col-sm-2 control-label"><my:Locale value="page.admin.pincourse"/></label>
                                    <div class="col-sm-8">
                                        <select name="idCourse" id="dropdownMenu" class="form-control">
                                            <c:forEach items="${sessionScope.courses}" var="course">
                                                <option value="${course.idCourse}">${course.nameCourse}</option>>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="command" value="createLecturerCommand">
                                <button type="submit" class="spacebtn btn btn-success"><span class="glyphicon glyphicon-plus"></span> <my:Locale value="page.admin.lecturer.create.title"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>