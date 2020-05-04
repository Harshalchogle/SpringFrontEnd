<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags" %>
<html>
<body>
<jsp:include page="header.jsp"/>
<h1>All Products</h1>
<table class="table">
<tr>
<td>Productid</td>
<td>Product Name</td>
<td>Price</td>
<td>Quantity</td>
<td>Photo</td>
</tr>
<c:forEach items="${products}" var="product">
<tr>
<td>${product.productid}</td>
<td>${product.productname}</td>
<td>${product.price}</td>
<td>${product.quantity}</td>
<td><img src='<sf:url value="/images/${product.productname}/${product.productimage}"></sf:url>' height="5%" width="5%"></td>
<td><a href="${pageContext.request.contextPath}/products/edit?productid=${product.productid}" class="btn btn-primary">Edit</a>|
<a href="${pageContext.request.contextPath}/products/delete?productid=${product.productid}" class="btn btn-danger">Delete</a>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>
