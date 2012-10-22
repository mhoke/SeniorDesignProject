<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Admin - Add Course</title>
		
		<script type="text/javascript" src="/webroot/static/js/jquery-validate-1.8.1.js"></script>
	</head>
	
	<body>	
		<a href="/Whiteboard/admin/home">Admin Home</a> <br />
		<a href="/Whiteboard/admin/approveCourse">Add Course</a> <br />
		<a href="/Whiteboard/admin/login">Logout</a> <p />
		
		<form action="${pageContext.servletContext.contextPath}/admin/approveCourse" method="post">
			<input type="hidden" name="submitted" value="true" />
	
			<table>
				<tr><td>Course Name:</td><td><input type="text" name="courseName" size="25"/></td></tr>
				<tr><td>Professor Id:</td><td><input type="text" class="digits" name="professorId" size="25" /></td></tr>
				<tr><td>Time:</td><td><input type="text" name="time" size="25" /></td></tr>
				<tr><td>Course Number:</td><td><input type="text" name="courseNumber" size="25" /></td></tr>
				<tr><td>Section Number:</td><td><input type="text" name="sectionNumber" size="25" /></td></tr>
				<tr><td>Credits:</td><td><input type="text" name="credits" size="25" /></td></tr>
				<tr><td>Days:</td><td><input type="text" name="days" size="25" /></td></tr>
				<tr><td>Location:</td><td><input type="text" name="location" size="25" /></td></tr>
				<tr><td>CRN:</td><td><input type="text" name="CRN" size="25" /></td></tr>
				<tr><td>Description:</td><td><input type="text" name="description" size="25" /></td></tr>
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