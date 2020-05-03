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
<div class="conatiner">
<h1>Change Password</h1>
<form action='<sf:url value="/users/confirmPassword"></sf:url>' method="post">
<div class="form-group">
<label>Enter Password</label>
<input type="password" name="pass" class="form-control">
</div>
<div class="form-group">
<label>Confirm Password</label>
<input type="password" name="cpass" class="form-control">
</div>
<input type="submit" value="Update Password" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</form>
</div>
</body>
</html>