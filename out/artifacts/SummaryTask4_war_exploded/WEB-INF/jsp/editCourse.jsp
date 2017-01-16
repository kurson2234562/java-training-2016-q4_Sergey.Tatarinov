<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
<%@ taglib prefix="course" uri="/WEB-INF/editcourse.tld" %>
<html>
    <head>
        <title> <my:Locale value="page.editcourse.title"/> </title>
        <link rel="stylesheet" type="text/css" href="../../styles/admin.css">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">

    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf"%>
        <div id="content">
            <%@ include file="/WEB-INF/jspf/about.jspf"%>
            <div class="list">
                <form>
                    <input type="hidden" name="command" value="updateCourseCommand">
                    <p><my:Locale value="page.editcourse.title"/></p>
                    <table>
                        <tr>
                            <th><my:Locale value="page.admin.create.namecourse"/></th>
                            <th><my:Locale value="page.admin.create.duration"/></th>
                            <th><my:Locale value="page.admin.create.lecturer"/></th>
                            <th><my:Locale value="page.admin.create.nametheme"/></th>
                            <th><my:Locale value="page.admin.create.namestatus"/></th>
                        </tr>
                        <course:editCourse method="update"/>
                    </table>
                    <input type="submit" value="<my:Locale value="page.editcourse.title"/>">
                </form>
            </div>
        </div>
    </body>
</html>
