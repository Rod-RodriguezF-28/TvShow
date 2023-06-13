<%--
  Created by IntelliJ IDEA.
  User: rod
  Date: 06-06-2023
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">
    <title>Show Programa id</title>
</head>
<body>
<c:if test="${not empty success}">
    <div class="alert alert-success">
        <c:out value="${success}"/>
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
    <h1 class="pb-3">${programa.titulo}</h1>
    <p class="pt-3">Network: ${programa.network}</p>
    <h3 class="pt-3">Users who rated this show</h3>
    <table class="table pt-3">
        <thead>
        <tr>
            <th>Name</th>
            <th>Rating</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${calificaciones}" var="calificacion">
            <tr>
                <td>${calificacion.user.name}</td>
                <td>${calificacion.rating}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-secondary mt-3 mb-3" href="/shows/${programa.id}/edit">Edit</a>

    <form:form action="/shows/${programa.id}/${usuario.id}" method="POST" class="w-25" modelAttribute="calificacion">
        <div class="row g-3 align-items-center">
            <div class="col-auto">
                <form:label path="rating" for="rating" class="col-form-label">Leave a Rating</form:label>
            </div>
            <div class="col-auto">
                <form:input path="rating" type="number" value="0" step="0.1" class="form-control" id="rating"
                            name="rating"
                            style="width: 60px"/>
                <form:errors class="text-danger" path="rating"></form:errors>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-secondary">Rate!</button>
            </div>
        </div>
    </form:form>

</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>
</html>
