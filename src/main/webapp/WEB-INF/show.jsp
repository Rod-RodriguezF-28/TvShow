<%--
  Created by IntelliJ IDEA.
  User: rod
  Date: 06-06-2023
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">
    <title>Show</title>
</head>
<body>
<c:if test="${not empty success}">
    <div class="alert alert-success">
        <c:out value="${success}"/>
    </div>
</c:if>
<c:if test="${not empty warning}">
    <div class="alert alert-warning">
        <c:out value="${warning}"/>
    </div>
</c:if>
<c:if test="${not empty danger}">
    <div class="alert alert-danger">
        <c:out value="${danger}"/>
    </div>
</c:if>
<div class="container">
    <div class="row pt-3">
        <div class="col pt-3"><a class="btn btn-success" href="${pageContext.request.contextPath}/show">Volver</a></div>
        <div class="col pt-3"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
    </div>
    <h1 class="pt-5">Welcome, ${usuario.name}</h1>
    <h3 class="pt-3">TV Shows</h3>

    <table class="table pt-3">
        <thead>
        <tr>
            <th>Show</th>
            <th>Network</th>
            <th>AVG Rating</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${programas}" var="progra">
            <tr>
                <td><a href="/shows/${progra.id}">${progra.titulo}</a></td>
                <td>${progra.network}</td>
                <td>
                    <c:set var="totalRating" value="0"/>
                    <c:set var="numCalificaciones" value="${fn:length(progra.calificaciones)}"/>
                    <c:forEach items="${progra.calificaciones}" var="calificacion">
                        <c:set var="totalRating" value="${totalRating + calificacion.rating}"/>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${numCalificaciones > 0}">
                            <c:set var="avgRating" value="${totalRating / numCalificaciones}"/>
                            <c:out value="${avgRating}"/>
                        </c:when>
                        <c:otherwise>
                            No ratings yet
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/shows/new">Add a show</a>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>
</html>
