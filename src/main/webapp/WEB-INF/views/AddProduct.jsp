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
<div class="container">
<sp:form action="${pageContext.request.contextPath}/products/add" modelAttribute="products" method="post">
<h1>Add Product</h1>
<div class="form-group">
<label>Enter Productname</label>
<sp:input path="productname" cssClass="form-control"/>
</div>
<div class="form-group">
<label>Enter price</label>
<sp:input path="price" cssClass="form-control"/>
</div>
<div class="form-group">
<label>Enter quantity</label>
<sp:input path="quantity" cssClass="form-control"/>
</div>
<input type="submit" value="Add Product" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</sp:form>
</div>
