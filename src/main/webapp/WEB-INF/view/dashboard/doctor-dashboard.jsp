<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.luv2code.springdemo.utils.SortUtils" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" >
    <title>Doctor Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"
    >
  </head>
<body>

<c:url var="editProfileLink" value="/doctor/showEditForm">
	<c:param name="doctorId" value="${doctor.id}"></c:param>
</c:url>

<c:url var="viewAppointmenstLink" value="/doctor/viewAppointments">
	<c:param name="doctorId" value="${doctor.id}"></c:param>
</c:url>

    <div class="three ui buttons">
      <button class="ui button">HOME</button>
      <a class="ui button" href="${editProfileLink}">EDIT PROFILE</a>
      <a class="ui button" href="${pageContext.request.contextPath}/doctor/logout">LOGOUT</a>
    </div>
      <hr>
      <div class="ui blue message">
          <div class=" header">
            <center>Welcome Dr. ${doctor.firstName} ${doctor.lastName}</center>
          </div>
          <p><center>Check Any Appointments booked with you below.</center></p>

     </div>
     <div style="background-image: url('${pageContext.request.contextPath}/resources/images/background.jpg');
                  background-repeat:no-repeat;
                  background-size: cover; 
                  text-align: center;">
                  <br>
        <a class="ui inverted red button" href="${viewAppointmenstLink}"><center>VIEW APPOINTMENTS</center></a>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
     </div>

     <br><br><br><br>
     <footer>
     <div class="ui blue inverted vertical footer segment">
        <div class="ui container" style="text-align: center;">
          <h3>Copyright &copy; Online Doctor Appointment System</h3>
        </div>
      </div>
      </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js">

    </script>
  </body>
</html>