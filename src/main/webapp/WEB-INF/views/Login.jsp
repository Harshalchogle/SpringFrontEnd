<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags"%>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<form action='<sf:url value="/loginaction"></sf:url>' method="post">
<h1>Login Form</h1>
<div class="form-group">
<label>Enter Username</label>
<input type="text" name="username" class="form-control">
</div>
<div class="form-group">
<label>Enter Password</label>
<input type="password" name="password" class="form-control">
</div>
<a href="${pageContext.request.contextPath}/users/username">Forget Password?</a><br>
<input type="submit" value="Login" class="btn btn-primary">
<input type="reset" value="Reset" class="btn btn-danger">
</form>
</div>
</body>
</html>