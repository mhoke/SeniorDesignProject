<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Admin - Add Course</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>

		<style type="text/css">
			label.error, .error {color: red;}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
			  	$("#addCourseForm").validate();
			});
		</script>
	</head>
	
	<body>	
		<a href="/Whiteboard/admin/home">Admin Home</a> <br />
		<a href="/Whiteboard/admin/approveCourse">Add Course</a> <br />
		<a href="/Whiteboard/admin/changeUserType">Change User Type</a> <br />
		<a href="/Whiteboard/admin/login">Logout</a> <p />
		
		<form id="addCourseForm" action="${pageContext.servletContext.contextPath}/admin/approveCourse" method="post">
			<input type="hidden" name="submitted" value="true" />
	
			<table>
				<tr><td>Course Name:</td><td><input type="text" class="required" name="courseName" size="25"/></td></tr>
				<tr><td>Professor Name:</td><td><input type="text" class="required" name="professorName" size="25" /></td></tr>
				<tr><td>Email Address:</td><td><input type="text" class="required email" name="emailAddress" size="25" /></td></tr>
				<tr><td>Time:</td><td><input type="text" class="required" name="time" size="25" /></td></tr>
				<tr><td>Course Number:</td><td><input type="text" class="required digits" name="courseNumber" minlength="3" maxlength="3" size="25" /></td></tr>
				<tr><td>Section Number:</td><td><input type="text" class="required digits" name="sectionNumber" minlength="3" maxlength="3" size="25" /></td></tr>
				<tr><td>Credits:</td><td><input type="text" class="required digits" name="credits" minlength="1" maxlength="1"size="25" /></td></tr>
				<tr><td>Days:</td><td><input type="text" class="required" name="days" size="25" /></td></tr>
				<tr><td>Location:</td><td><input type="text" class="required" name="location" size="25" /></td></tr>
				<tr><td>CRN:</td><td><input type="text" class="required digits" name="CRN" maxlength="5" size="25" /></td></tr>
				<tr><td>Description:</td><td><input type="text"  class="required" name="description" size="25" /></td></tr>
				<tr><td><input name="addCourseButton" type="submit" value="Add Course" /></td>
			</table>
		</form>
		
		<c:if test="${! empty errorMessage }">
			<p class="error">${errorMessage }</p>
		</c:if>
		<c:if test="${! empty updateMessage }">
			<p>${updateMessage }</p>
		</c:if>
	</body>
</html>