<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.springdemo.utils.SortUtils" %>
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
	
	<title>Book Appointment</title>
		
	<style>
		.error {color:red}
	</style>
	
</head>
<body>
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">
		
				<div class="panel-heading">
					<div class="panel-title">Book Appointment</div>
				</div>
		
				<div style="padding-top: 30px" class="panel-body">
				
				<p>Doctor: ${appointment.doctor.firstName} ${appointment.doctor.lastName}</p>
				<p>Patient: ${appointment.patient.firstName} ${appointment.patient.lastName}</p>
		
				<form:form action="saveAppointment/${appointment.patient.id}/${appointment.doctor.id}" modelAttribute="appointment" method="POST">
				
						<form:hidden path="id"></form:hidden>
		
						<form:input style="margin-bottom: 25px" path="reason" class="form-control mb-4 col-4" placeholder="Reason"/>
						
						<form:input style="margin-bottom: 25px" path="date" class="form-control mb-4 col-4" placeholder="date(dd/mm/yy)"/>
						
					
						<label style="margin-bottom: 25px">Time Slot: </label>
							<form:select path="time" class="form-select" style="margin-bottom: 25px">
							<form:option value="10:00 AM" label="10:00 AM"/>
							<form:option value="11:00 AM" label="11:00 AM"/>
							<form:option value="12:00 PM" label="12:00 PM"/>
							<form:option value="01:00 PM" label="01:00 PM"/>
							<form:option value="02:00 PM" label="02:00 PM"/>
							<form:option value="03:00 PM" label="03:00 PM"/>
							<form:option value="04:00 PM" label="04:00 PM"/>
							<form:option value="05:00 PM" label="05:00 PM"/>
							<form:option value="06:00 PM" label="06:00 PM"/>
							</form:select>
							
						<br>
						
						<label style="margin-bottom: 25px">Mode: </label>
							<form:select path="mode" class="form-select" style="margin-bottom: 25px">
							<form:option value="Online" label="Online"/>
							<form:option value="At Clinic" label="At Clinic"/>
							</form:select>
							<br>
												
						<button type="submit" class="btn btn-primary">Book</button>
		</form:form>
		
		<hr>
		
		<c:url var="listLink" value="/patient/bookAppointment">
			<c:param name="patientId" value="${appointment.patient.id}"></c:param>
		</c:url>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${listLink}" class="btn btn-outline-primary">Back to List</a> 
		</p>
				
		</div>
		</div>
		</div>
				
</body>
</html>