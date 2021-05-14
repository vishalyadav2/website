<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<sql:setDataSource driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/moobile"
					   user="root"
					   password=""
					   var="con"/>

	<!-- Display only particular phone -->
	<!-- Take phoneid from last page phones.jsp using ${param.phoneid} -->   
	<sql:query var="rs" dataSource="${con}">
		select * from phone where phoneid=?
		<sql:param>${param.phoneid}</sql:param>
	</sql:query>

	<div class="container">
		<div class="row">
			<c:forEach items="${rs.rows}" var="row">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<a href="phone.jsp?phoneid=${row.phoneid}"><img src="ImageServlet?id=${row.phoneid}" class="col-lg-12"/></a>
				</div>
				<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
					<h3 class="col-lg-12">${row.phonename}</h3>
					<h5 class="col-lg-12">${row.detail}</h5>
					<h5 class="col-lg-12">Rs. ${row.price}</h5>
					<form action="AddToCartServlet" method="post">
						<input type="text" name="phoneid" value="${row.phoneid}" hidden/>
						Quantity: <input type="number" name="qty"/>
						<input type="submit" class="btn btn-success btn-block" value="Add To Cart">
					</form>

					<!-- Display Buy button only if price is greater than zero -->
					<!-- Otherwise display read button because phone is free -->
					<c:if test="${row.price>0}">
						<a href="OrderController?phoneid=${row.phoneid}" class="btn btn-primary btn-block">Buy</a>
					</c:if>

					<!-- To read phone add valid google drivelink in database -->
					<!-- Either while adding new phone in the table or update the existing link -->
					
						<a href="${row.link}" target="_blank" class="btn btn-secondary btn-block">Video</a>
						<a href="${row.photo}" target="_blank" class="btn btn-secondary btn-block">Photo</a>
						<a href="${row.detailp}" target="_blank" class="btn btn-secondary btn-block">Product Detail</a>
					
					<!-- Url Rewriting Technique -->
					<c:if test="${u.role=='admin'}">
						<a href="DeleteServlet?phoneid=${row.phoneid}" class="btn btn-danger btn-block">Delete</a>
						<a href="edit.jsp?phoneid=${row.phoneid}" class="btn btn-warning btn-block">Edit</a>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>	    
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>