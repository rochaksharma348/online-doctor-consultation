<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  		<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<meta charset="ISO-8859-1">
	
	<title>Update Doctor</title>
		
	<style>
		.error {color:red}
	</style>
	
</head>
<body>
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">
		
				<div class="panel-heading">
					<div class="panel-title">Add Doctor</div>
				</div>
		
				<div style="padding-top: 30px" class="panel-body">
		
				<form:form action="updateDoctor" modelAttribute="doctor" method="POST">
		
						<form:hidden path="id"></form:hidden>
		
						<form:input path="firstName" class="form-control mb-4 col-4" placeholder="First name"/>
						<form:errors path="firstName" cssClass="error"/>
						<br>	
					
						<form:input path="lastName" class="form-control mb-4 col-4" placeholder="Last name"/>
						<form:errors path="lastName" cssClass="error"/>	
						<br>
					
						<form:input path="email" class="form-control mb-4 col-4" placeholder="email"/>
						<form:errors path="email" cssClass="error"/>
						<br>
					
						<form:input path="username" class="form-control mb-4 col-4" placeholder="username"/>
						<form:errors path="username" cssClass="error"/>	
						<br>
					
						<form:input type="password" path="password" class="form-control mb-4 col-4" placeholder="Password"/>
						<form:errors path="password" cssClass="error"/>	
						<br>
		
						<form:input path="dateOfBirthString" class="form-control mb-4 col-4" placeholder="dd/mm/yy"/>
						<form:errors path="dateOfBirth" cssClass="error"/>	
						<br>
					
						<label style="margin-bottom: 25px">Gender: </label>
							<form:select path="gender" class="form-select" style="margin-bottom: 25px">
							<form:option value="male" label="male"/>
							<form:option value="female" label="female"/>
							</form:select>
							<form:errors path="gender" cssClass="error"/>
							
						<br>
							
						<form:input path="location" class="form-control mb-4 col-4" placeholder="Location"/>
						<form:errors path="location" cssClass="error"/>	
						<br>
					
						<form:input path="phoneNo" class="form-control mb-4 col-4" placeholder="Phone Number"/>
						<form:errors path="phoneNo" cssClass="error"/>	
						<br>
					
						<form:input path="speciality" class="form-control mb-4 col-4" placeholder="Speciality"/>
						<form:errors path="speciality" cssClass="error"/>	
						<br>
					
						<form:input path="address" class="form-control mb-4 col-4" placeholder="Address"/>
						<form:errors path="address" cssClass="error"/>	
						<br>
					
						<button type="submit" class="btn btn-primary">Save</button>
		</form:form>
		
		<hr>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/doctor/list" class="btn btn-outline-primary">Back to List</a> 
		</p>
				
		</div>
		</div>
		</div>
				
</body>
</html>