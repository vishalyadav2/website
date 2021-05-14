<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.myclass
	{
		margin:0 auto;
	}
</style>
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

		<c:forEach items="${rs.rows}" var="row">
		<form action='UpdatePhoneServlet' method='post' class="myclass col-lg-6 col-md-9 col-sm-12 col-xs-12">
		<div class="form-group">
				<label>Enter Phone id</label>
				<input type="text" name="phoneid" value="${row.phoneid}" placeholder="Enter phoneid" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Phone Name</label>
				<input type="text" name="phonename" value="${row.phonename}" placeholder="Enter PhoneName" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Detail</label>
				<input type="text" name="detail" value="${row.detail}" placeholder="Enter Detail" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Price</label>
				<input type="text" name="price" value="${row.price}" placeholder="Enter Price" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Link</label>
				<input type="text" name="link" value="${row.link}"  placeholder="Enter Link" class="form-control"/>
			</div>
			<div class="form-group">
				<input type="submit" value="Update Phone" class="btn btn-primary btn-block"/>
				<input type="reset" value="Reset" class="btn btn-danger btn-block"/>
			</div>
		</form>
		</c:forEach>
		<jsp:include page="footer.jsp"></jsp:include>
		</div>
</body>
</html> 