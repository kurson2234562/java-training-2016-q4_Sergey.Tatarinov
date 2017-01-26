<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" uri="/WEB-INF/locale.tld"%>
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
                <%@ include file="/WEB-INF/jspf/endabout.jspf"%>
            </div>
            <div class="col-lg-9">
                <div class="panel panel-primary">
                    <div class="panel-heading">Information</div>
                    <form method="post" class="form-horizontal">
                        <c:choose>
                            <c:when test="${not empty userinfo.login}">
                                <div class="form-group">
                                    <label for="login" class="col-sm-2 control-label"><my:Locale value="page.people.login"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="login" id="login" placeholder="<my:Locale value="page.people.login"/>" value="${userinfo.login}" required/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="login" class="col-sm-2 control-label"><my:Locale value="page.people.login"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="login" id="login" placeholder="<my:Locale value="page.people.login"/>" required/>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty userinfo.email}">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${userinfo.email}"/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input type="email" class="form-control" name="email" id="email" placeholder="Email" />
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty info.surname}">
                                <div class="form-group">
                                    <label for="surname" class="col-sm-2 control-label"><my:Locale value="page.people.surname"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="surname" id="surname" placeholder="<my:Locale value="page.people.surname"/>" value="${info.surname}" required/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="surname" class="col-sm-2 control-label"><my:Locale value="page.people.surname"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="surname" id="surname" placeholder="<my:Locale value="page.people.surname"/>" required/>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty info.name}">
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label"><my:Locale value="page.people.name"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="<my:Locale value="page.people.name"/>" value="${info.name}" required/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label"><my:Locale value="page.people.name"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="<my:Locale value="page.people.name"/>" required/>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty info.patronymic}">
                                <div class="form-group">
                                    <label for="patronymic" class="col-sm-2 control-label"><my:Locale value="page.people.patronymic"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="patronymic" id="patronymic" placeholder="<my:Locale value="page.people.patronymic"/>" value="${info.patronymic}"/>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group">
                                    <label for="patronymic" class="col-sm-2 control-label"><my:Locale value="page.people.patronymic"/></label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="patronymic" id="patronymic" placeholder="<my:Locale value="page.people.patronymic"/>"/>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="command" value="editInformationCommand">
                        <button type="submit" class="spacebtn btn btn-success">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>