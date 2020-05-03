<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<form action='<sf:url value="/users/username"></sf:url>' method="post">
<h1>Username</h1>
<div class="form-group">
<input type="text" name="username" class="form-control">
</div>
<input type="submit" value="Send OTP" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</form>
</div>
</body>
</html>