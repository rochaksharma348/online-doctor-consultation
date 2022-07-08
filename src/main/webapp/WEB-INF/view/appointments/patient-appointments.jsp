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
	<title>Appointments List</title>
</head>
<body>

<div class="container">
	
	<h2>${patient.firstName}'s Appointments</h2>
	<hr>
	
	<div>
		<div id="content">
		
			<table class="table table-bordered table-striped">
				<thead class="thead-dark">
				<tr>
					<th> Appointment Id </th>
					<th> Doctor </th>
					<th> Reason </th>
					<th> Date </th>
					<th> Time Slot </th>
					<th> Mode </th>
					<th> Update/Cancel Appointment </th>
				</tr>
				</thead>
				
				<c:forEach var="tempAppointment" items="${appointments}">
				
					<c:url var="updateAppointmentLink" value="/patient/showUpdateAppointmentForm">
						<c:param name="appointmentId" value="${tempAppointment.id}"></c:param>
					</c:url>
					
					<c:url var="deleteAppointmentLink" value="/patient/deleteAppointment">
						<c:param name="appointmentId" value="${tempAppointment.id}"></c:param>
					</c:url>
					
					<tr>
						<td>${tempAppointment.id}</td>
						<td>${tempAppointment.doctor.firstName} ${tempAppointment.doctor.lastName}</td>
						<td>${tempAppointment.reason}</td>
						<td>${tempAppointment.date}</td>
						<td>${tempAppointment.time}</td>
						<td>${tempAppointment.mode}</td>
						
						<td>
							<a href="${updateAppointmentLink}" class="btn btn-info btn-sm">Update Appointment</a>
							<a href="${deleteAppointmentLink}" class="btn btn-danger btn-sm" onclick="if(!(confirm('Are you sure you want to canel this appointment?'))) return false">Cancel Appointment</a>
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