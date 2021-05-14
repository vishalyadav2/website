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
					   
	<c:if test="${param.sort!=null}">
	<c:if test="${param.sort=='hightolow'}">	
	<sql:query var="rs" dataSource="${con}">
		select * from phone where status='A' order by price desc
	</sql:query>
	</c:if>

	<c:if test="${param.sort=='lowtohigh'}">
	<sql:query var="rs" dataSource="${con}">
		select * from phone where status='A' order by price ASC
	</sql:query>
	</c:if>
	</c:if>

	<c:if test="${param.phonename!=null && param.sort==null}">	
	<sql:query var="rs" dataSource="${con}">
		select * from phone where status='A' and phonename like '%' ? '%' 
		<sql:param>${param.phonename}</sql:param>
	</sql:query>
	</c:if>

	<c:if test="${param.phonename==null && param.sort==null}">

	<sql:query var="rs" dataSource="${con}">
		select * from Phone where status='A'
	</sql:query>
	
	</c:if>
	

	<div class="container">
		<div class="row">
			<c:forEach items="${rs.rows}" var="row">
				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<a href="phone.jsp?phoneid=${row.phoneid}"><img src="ImageServlet?id=${row.phoneid}" style="height:300px" class="col-lg-12"/></a>
					<h3 class="col-lg-12">${row.phonename}</h3>
					<h4 class="col-lg-12"> ${row.detail}</h4>
					<h4 class="col-lg-12">Rs. ${row.price}</h4>
					<a href="AddToCartServlet" class="btn btn-success btn-block">Add To Cart</a>
					<a href="OrderController" class="btn btn-danger btn-block">Buy</a>
				</div>
			</c:forEach>
		</div>
	</div>	    
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html> 