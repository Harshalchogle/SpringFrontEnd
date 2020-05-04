<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
    <%@page isELIgnored="false" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<c:if test="${p==null}">
<sp:form action="${pageContext.request.contextPath}/products/add" modelAttribute="products" method="post"  enctype="multipart/form-data">
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
<div class="form-group">
<label>Add Product Photo</label>
<input type="file" name="productPhoto" accept="imgae/*">
</div>
<input type="submit" value="Add Product" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</sp:form>
</c:if>
<c:if test="${p!=null}">
<sp:form action="${pageContext.request.contextPath}/products/update" modelAttribute="p" method="post"  enctype="multipart/form-data">
<h1>update Product</h1>
<div class="form-group">
<sp:hidden path="productid" cssClass="form-control"/>
</div>
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
<div class="form-group">
<label>Add Product Photo</label>
<input type="file" name="productPhoto" accept="imgae/*">
</div>
<input type="submit" value="Update Product" class="btn btn-success">
<input type="reset" value="Reset" class="btn btn-danger">
</sp:form>
</c:if>
</div>
</body>
</html>

