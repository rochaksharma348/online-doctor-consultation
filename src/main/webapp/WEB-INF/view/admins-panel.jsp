<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admins panel</title>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<div>
		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-info">

				<div class="panel-heading">
					<div class="panel-title">Admin Sign In</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
				
					<form:form action="processLogin" modelAttribute="adminCredentials" method="POST" class="form-horizontal">
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							
							<form:input type="text" path="username" placeholder="username" class="form-control"/>
						</div>
						
							<div style="margin:botton: 25px" class="text-danger">
								<form:errors path="username" class="error"/>
							</div>
						

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
							
							<form:input type="password" path="password" placeholder="password" class="form-control"></form:input>
						</div>
						
							<div style="margin:botton: 20px" class="text-danger">
								<form:errors path="username" class="error"/>
							</div>

						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-success">Login</button>
							</div>
						</div>			
					</form:form>
					
					<hr>
			
					<div style="clear; both;"></div>
								
						<p>
							<a href="${pageContext.request.contextPath}" class="btn btn-outline-primary">Back to home page</a> 
						</p>
			
					</div>
				
			</div>
			
		</div>
	
	</div>
	
</body>
</html>





<!-- 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin panel</title>

	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css">
		
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/add-patient-style.css">
		
	<style type="text/css">
	.error{color:red}
	</style>
	

</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Admin panel</h2>
		</div>
	</div>
	
	<div id="container">
		<form:form action="processLogin" modelAttribute="adminCredentials" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><form:input path="username"/>
						<form:errors path="username" cssClass="error"/></td>							
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><form:password path="password"/>
						<form:errors path="password" cssClass="error"/></td>	
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Login" class="save"></td>	
					</tr>
				</tbody>
			</table>			
		</form:form>
	</div>
	<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}">Back to home page</a> 
		</p>

</body>
</html>  -->