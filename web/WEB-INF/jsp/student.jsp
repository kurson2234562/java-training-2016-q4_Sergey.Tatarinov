<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tables.tld"%>
<%@ taglib prefix="cr" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="pr" uri="/WEB-INF/progress.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><my:Locale value="page.student.title"/></title>
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <script src="../../bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <link rel="stylesheet" type="text/css" href="../../styles/student.css">
        <div class="container-fluid bs-const">
            <div class="col-lg-3">
                <%@ include file="/WEB-INF/jspf/about.jspf"%>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <div class="cont">
                        <c:forEach items="${coursesForUser}" var="courseForUser">
                            <div class="col-sm-2 col-md-3">
                                <div class="thumbnail">
                                    <c:choose>
                                        <c:when test="${courseForUser.nameCourse eq 'Java Web developing'}">
                                            <img src="/img/courses/java.png" alt="Java">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'PHP developing'}">
                                            <img src="/img/courses/php.png" alt="PHP">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'Kotlin developing'}">
                                            <img src="/img/courses/kotlin.png" alt="Kotlin">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'Python developing'}">
                                            <img src="/img/courses/python.png" alt="Python">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'Ruby developing'}">
                                            <img src="/img/courses/ruby.png" alt="Python">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'HTML+CSS developing'}">
                                            <img src="/img/courses/htmlcss.png" alt="Python">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'Scala developing'}">
                                            <img src="/img/courses/scala.png" alt="Scala">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'C# developing'}">
                                            <img src="/img/courses/csharp.png" alt="C#">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'ASP.Net'}">
                                            <img src="/img/courses/aspnet.png" alt="ASP.Net">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'QA testing'}">
                                            <img src="/img/courses/qa.jpeg" alt="QA">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'JavaScript developing'}">
                                            <img src="/img/courses/js.png" alt="JS">
                                        </c:when>
                                        <c:when test="${courseForUser.nameCourse eq 'Groovy developing'}">
                                            <img src="/img/courses/groovy.png" alt="Groovy">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="" alt="?">
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="caption">
                                        <form>
                                            <input type="hidden" name="command" value="registerOnCourseCommand">
                                            <input type="hidden" name="idCourse" value="${courseForUser.idCourse}">
                                            <h3>${courseForUser.nameCourse}</h3>
                                            <p>Длительность: ${courseForUser.duration} недели</p>
                                            <p>Начало: март 2017</p>
                                            <p>
                                                <a onclick="$(this).closest('form').submit()" href="#" class="btn btn-success" role="button">Зарегистрироваться</a>
                                            </p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><my:Locale value="page.student.table.title.opened"/></div>
                        <div class="table-responsive">
                            <t:table status="Opened"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><my:Locale value="page.student.table.title.inprogress"/> </div>
                        <div class="table-responsive">
                            <t:table status="In progress"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><my:Locale value="page.student.table.title.progress"/></div>
                        <div class="table-responsive">
                            <pr:progress/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

