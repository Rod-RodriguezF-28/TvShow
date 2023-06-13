<%--
  Created by IntelliJ IDEA.
  User: rod
  Date: 01-06-2023
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">
    <title>Registrar y Autenticar</title>
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
    <div class="row pt-5">
        <div class="col-md-6 ms-4">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title text-center text-primary">Registro de Usuarios</h3>
                </div>
                <div class="card-body">
                    <!-- View -->
                    <form:form method="post" action="${pageContext.request.contextPath}/registration"
                               modelAttribute="user">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="mb-3">
                                    <form:label path="name" for="name" class="form-label">Name:</form:label>
                                    <form:input path="name" type="text" id="name" name="name" class="form-control"/>
                                    <!-- Display errors for name field -->
                                    <form:errors path="name" cssClass="text-danger"/>
                                </div>

                                <div class="mb-3">
                                    <form:label path="email" for="email" class="form-label">Email:</form:label>
                                    <form:input path="email" type="email" id="email" name="email" class="form-control"/>
                                    <!-- Display errors for email field -->
                                    <form:errors path="email" cssClass="text-danger"/>
                                </div>
                                <div class="mb-3">
                                    <form:label path="password" for="password" class="form-label">Password:</form:label>
                                    <form:input path="password" type="password" id="password" name="password"
                                                class="form-control"/>
                                    <!-- Display errors for password field -->
                                    <form:errors path="password" cssClass="text-danger"/>
                                </div>
                                <div class="mb-3">
                                    <form:label path="passwordConfirmation" for="passwordConfirmation"
                                                class="form-label">Password Confirmation:</form:label>
                                    <form:input path="passwordConfirmation" type="password" id="passwordConfirmation"
                                                name="passwordConfirmation"
                                                class="form-control"/>
                                    <!-- Display errors for passwordConfirmation field -->
                                    <form:errors path="passwordConfirmation" cssClass="text-danger"/>
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">Register</button>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <div class="col-md-5 ms-5">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title text-center text-success">Login de Usuarios</h3>
                </div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="loginUser">
                    <div class="mb-3">
                            <form:label path="emailLogin" for="emailLogin" class="form-label">Email:</form:label>
                            <form:input path="emailLogin" type="email" class="form-control" id="emailLogin"
                                        placeholder="Ingrese su email"/>
                            <form:errors class="text-danger" path="emailLogin"></form:errors>
                        </div>
                        <div class="mb-3">
                            <form:label path="passwordLogin" for="passwordLogin"
                                        class="form-label">Contraseña:</form:label>
                            <form:input path="passwordLogin" type="password" class="form-control" id="passwordLogin"
                                        placeholder="Ingrese su contraseña"/>
                            <form:errors class="text-danger" path="passwordLogin"></form:errors>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-success">Login</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>
</body>
</html>