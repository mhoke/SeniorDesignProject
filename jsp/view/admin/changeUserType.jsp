<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>Admin - Change User Type</title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>

		<style type="text/css">
			label.error, .error {color: red;}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
			  	$("#changeUserTypeForm").validate();
			});
		</script>
	</head>
	
	<body>	
		<a href="/Whiteboard/admin/home">Admin Home</a> <br />
		<a href="/Whiteboard/admin/approveCourse">Add Course</a> <br />
		<a href="/Whiteboard/admin/changeUserType">Change User Type</a> <br />
		<a href="/Whiteboard/admin/login">Logout</a> <p />
		
		<form id="changeUserTypeForm" action="${pageContext.servletContext.contextPath}/admin/changeUserType" method="post">
			<input type="hidden" name="submitted" value="true" />
	
			<table>
				<tr><td>Name:</td><td><input type="text" class="required" name="name" size="25" /></td></tr>
				<tr><td>Email address:</td><td><input type="text" class="required email" name="emailAddress" size="25" /></td></tr>
				<tr><td>User Type:</td>
					<td><select name="userType">
  						<option>Student</option>
  						<option>Teacher</option>
 				 		<option>Both</option>
					</select></td>
				</tr>
				<tr><td><input name="acceptButton" type="submit" value="Accept" /></td><td><input name="rejectButton" type="submit" value="Reject" /></td></tr>
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