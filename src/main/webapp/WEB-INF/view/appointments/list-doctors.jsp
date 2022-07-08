<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.springdemo.utils.SortUtils" %>
<!DOCTYPE html>
<html>
<head>

	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


	<meta charset="ISO-8859-1">
	<title>List Doctors</title>
</head>
<body>

<div class="container">
	
	<h2>List of Doctors</h2>
	<hr>
	
	<div>
		<div id="content">
		
			<c:url var="searchLink" value="searchDoctor">
				<c:param name="patientId" value="${patient.id}"></c:param>
			</c:url>
				
			<form:form action="${pageContext.request.contextPath}/patient/searchDoctor/${patient.id}" method="GET" class="form-inline">
				<input class="form-control ml-8 mr-sm-2 mb-3" type="search" name="theSearchName" placeholder="Type name/location"/>
				<button class="btn btn-success mb-3" type="submit">Search</button>
			</form:form>
		
			<table class="table table-bordered table-striped">
				<thead class="thead-dark">
				<tr>
					<th> First Name </th>
					<th> Last Name </th>
					<th> Email </th>
					<th> username </th>
					<th> Speciality </th>
					<th> date of birth </th>
					<th> location </th>
					<th> phone no </th>
					<th> address </th>
					<th> gender </th>
					<th> Book Appointment </th>
				</tr>
				</thead>
				
				<c:forEach var="tempDoctor" items="${doctors}">
				
					<c:url var="processAppointmentLink" value="/patient/showAppointmentForm">
						<c:param name="doctorId" value="${tempDoctor.id}"></c:param>
						<c:param name="patientId" value="${patient.id}"></c:param>
					</c:url>
					
					<tr>
						<td>${tempDoctor.firstName}</td>
						<td>${tempDoctor.lastName}</td>
						<td>${tempDoctor.email}</td>
						<td>${tempDoctor.username}</td>
						<td>${tempDoctor.speciality}</td>
						<td>${tempDoctor.dateOfBirthString}</td>
						<td>${tempDoctor.location}</td>
						<td>${tempDoctor.phoneNo}</td>
						<td>${tempDoctor.address}</td>
						<td>${tempDoctor.gender}</td>
						
						<td>
							<a href="${processAppointmentLink}" class="btn btn-info btn-sm">Book Appointment</a>
						</td>
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<hr>
	
	<div style="clear; both;"></div>
	
	<c:url var="dashboardLink" value="/patient/dashboard">
		<c:param name="patientId" value="${patient.id}"></c:param>
	</c:url>
		
		<p>
			<a href="${dashboardLink}" class="btn btn-outline-primary">Back to dashboard</a> 
		</p>
</div>

</body>
</html>