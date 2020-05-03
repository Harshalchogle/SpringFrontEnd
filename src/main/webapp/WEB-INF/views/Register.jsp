<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<sp:form action="${pageContext.request.contextPath}/users/register" method="post" modelAttribute="users">
<div class="container">
<h1>Register Form</h1>
<div class="form-group">
<label>Enter username</label>
<sp:input path="username" cssClass="form-control"/>
</div>
<div class="form-group">
<label>Enter Email</label>
<sp:input path="email" cssClass="form-control"/>
</div>
<div class="form-group">
<label>Enter Password</label>
<sp:password path="password" cssClass="form-control"/>
</div>
<input type="submit" value="Register" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</div>
</sp:form>
</body>
</html>