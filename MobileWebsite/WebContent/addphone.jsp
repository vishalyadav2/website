<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<form action='AddPhoneServlet' method='post' enctype="multipart/form-data" class="myclass col-lg-6 col-md-9 col-sm-12 col-xs-12">
			<div class="form-group">
				<label>Enter Phone Name</label>
				<input type="text" name="phonename" placeholder="Enter PhoneName" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Detail</label>
				<input type="text" name="detail" placeholder="Enter Detail" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Price</label>
				<input type="text" name="price" placeholder="Enter Price" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Link</label>
				<input type="text" name="link" placeholder="Enter Link" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Select Image</label>
				<input type="file" name="image" placeholder="Select Image" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Photo Link</label>
				<input type="text" name="photo" placeholder="Enter Image Link" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Enter Detail Link</label>
				<input type="text" name="detailp" placeholder="Enter Detail Link" class="form-control"/>
			</div>
			<div class="form-group">
				<input type="submit" value="Add Phone" class="btn btn-primary btn-block"/>
				<input type="reset" value="Reset" class="btn btn-danger btn-block"/>
			</div>
		</form>

		<jsp:include page="footer.jsp"></jsp:include>
		</div>

</body>
</html> 