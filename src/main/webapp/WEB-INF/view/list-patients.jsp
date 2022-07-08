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
	<title>List Patients</title>
	
</head>
<body>


<div class="container">

	<h2>List of patients</h2>
	<hr>
	
	<div>
		<div id="content">
		
			<c:url var="sortUsingFirstName" value="/patient/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortUsingLastName" value="/patient/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortUsingEmail" value="/patient/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"></c:param>
			</c:url>
		
				
			<form:form action="search" method="GET" class="form-inline">
			
				<input type="button" value="Add Patient" 
				onclick="window.location.href='showFormForAddPatient'"
				class="btn btn-primary btn-sm mr-5 mb-3"/>
			
				<input class="form-control ml-8 mr-sm-2 mb-3" type="search" name="theSearchName" placeholder="Type name/location"/>
				<button class="btn btn-success mb-3" type="submit">Search</button>
			</form:form>
		
			<table class="table table-bordered table-striped">
				<thead class="thead-dark">
			
				<tr>
					<th><a href="${sortUsingFirstName}"> First Name </a></th>
					<th><a href="${sortUsingLastName}"> Last Name </a></th>
					<th><a href="${sortUsingEmail}"> Email </a></th>
					<th>username </th>
					<th>date of birth </th>
					<th>location </th>
					<th>phone no </th>
					<th>gender </th>
					<th>Action </th>
				</tr>
				
				</thead>
				
				<c:forEach var="tempPatient" items="${patients}">
				
					<c:url var="updateLink" value="/patient/showUpdateForm">
						<c:param name="patientId" value="${tempPatient.id}"></c:param>
					</c:url>
					
					<c:url var="deleteLink" value="/patient/delete">
						<c:param name="patientId" value="${tempPatient.id}"></c:param>
					</c:url>
				
					<tr>
						<td>${tempPatient.firstName}</td>
						<td>${tempPatient.lastName}</td>
						<td>${tempPatient.email}</td>
						<td>${tempPatient.username}</td>
						<td>${tempPatient.dateOfBirthString}</td>
						<td>${tempPatient.location}</td>
						<td>${tempPatient.phoneNo}</td>
						<td>${tempPatient.gender}</td>
						
						<td>
							<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
							<a href="${deleteLink}" class="btn btn-danger btn-sm" onclick="if(!(confirm('Are you sure you want to delete this patient?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<hr>
	
	<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-outline-primary">Back to dashboard</a> 
		</p>
</div>

</body>
</html>