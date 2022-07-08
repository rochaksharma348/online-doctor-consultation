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
		
			<c:url var="sortUsingFirstName" value="/doctor/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortUsingLastName" value="/doctor/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"></c:param>
			</c:url>
			
			<c:url var="sortUsingEmail" value="/doctor/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"></c:param>
			</c:url>
				
			<form:form action="search" method="GET" class="form-inline">
			
				<input type="button" value="Add Doctor" 
				onclick="window.location.href='showFormForAddDoctor'"
				class="btn btn-primary btn-sm mr-5 mb-3"/>
			
				<input class="form-control ml-8 mr-sm-2 mb-3" type="search" name="theSearchName" placeholder="Type name/location"/>
				<button class="btn btn-success mb-3" type="submit">Search</button>
			</form:form>
		
			<table class="table table-bordered table-striped">
				<thead class="thead-dark">
				<tr>
					<th><a href="${sortUsingFirstName}"> First Name </a> </th>
					<th><a href="${sortUsingLastName}"> Last Name </a></th>
					<th><a href="${sortUsingEmail}"> Email </a></th>
					<th> username </th>
					<th> Speciality </th>
					<th> date of birth </th>
					<th> location </th>
					<th> phone no </th>
					<th> address </th>
					<th> gender </th>
					<th> Action </th>
				</tr>
				</thead>
				
				<c:forEach var="tempDoctor" items="${doctors}">
				
					<c:url var="updateLink" value="/doctor/showUpdateForm">
						<c:param name="doctorId" value="${tempDoctor.id}"></c:param>
					</c:url>
					
					<c:url var="deleteLink" value="/doctor/delete">
						<c:param name="doctorId" value="${tempDoctor.id}"></c:param>
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
							<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
							<a href="${deleteLink}" class="btn btn-danger btn-sm" onclick="if(!(confirm('Are you sure you want to delete this doctor?'))) return false">Delete</a>
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