<%--
  Created by IntelliJ IDEA.
  User: rod
  Date: 06-06-2023
  Time: 15:49
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
    <title>Show New</title>
</head>
<body>

<div class="container">
    <div class="row pt-3">
        <div class="col pt-3"><a class="btn btn-success" href="${pageContext.request.contextPath}/show">Volver</a></div>
        <div class="col pt-3"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
    </div>
    <h1 class="pt-3 pb-3">Create a new show</h1>
    <form:form method="post" action="${pageContext.request.contextPath}/shows/new" modelAttribute="programa" class="w-50">
        <form:input path="user" type="hidden" value="${usuario.id}"/>
        <div class="row mb-3">
            <form:label path="titulo" for="titulo" class="col-sm-2 col-form-label">Titulo</form:label>
            <div class="col-sm-10">
                <form:input path="titulo" type="text" class="form-control" id="titulo" name="titulo" />
            </div>
            <form:errors class="text-danger" path="titulo"></form:errors>
        </div>
        <div class="row mb-3">
            <form:label path="network" for="network" class="col-sm-2 col-form-label">Network</form:label>
            <div class="col-sm-10">
                <form:input path="network" type="text" class="form-control" id="network" name="network" />
            </div>
            <form:errors class="text-danger" path="network"></form:errors>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <button type="submit" class="btn btn-secondary">Submit</button>
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
