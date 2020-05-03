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
<form action='<sf:url value="/users/checkOTP"></sf:url>' method="post">
<div class="form group">
<label>Enter OTP</label>
<input type="text" name="userOTP">
<input type="text" name="otp" value="${otp}">
</div>
<input type="submit" value="Confirm OTP" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</form>
</div>
</body>
</html>
</body>
</html>