<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="course" uri="/WEB-INF/editcourse.tld" %>
<html>
    <head>
        <title> <my:Locale value="page.editcourse.title"/> </title>
        <link rel="stylesheet" type="text/css" href="../../styles/editcourses.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
        <script src="../../bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div class="container-fluid bs-const">
            <div class="col-lg-3">
                <%@ include file="/WEB-INF/jspf/about.jspf"%>
            </div>
            <div class="col-lg-9">
                <div class="panel panel-primary table-responsive">
                    <div class="panel-heading"><my:Locale value="page.editcourse.title"/></div>
                    <form class="form-horizontal">
                        <input type="hidden" name="command" value="updateCourseCommand">
                        <table>
                            <course:editCourse method="update"/>
                        </table>
                        <button type="submit" class="spacebtn btn btn-success"><my:Locale value="page.editcourse.title"/></button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
