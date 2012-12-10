<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Add Assignment</Title>
		
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
			  	$("#addAssignmentForm").validate();
			});
		</script>
	</head>
	
	<body>
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar"> Bus Schedule <br/><A HREF="campusMap">Campus Map</A></div>
		<div class="body">
			<c:if test="${!empty errorMessage }">
				<div class="error">${errorMessage}</div>
			</c:if>
						
			<form id ="addAssignmentForm" action="${pageContext.servletContext.contextPath}/addAssignment" method="post">
				<table>
					<tr>
						<td class="text">Name: </td>
						<td> <input class="required" type="text" name="nameBox" size="12" /></td>
					</tr>
					<tr>
						<td class="text">Due Date: </td>
						<td> <input class="required" type="text" name="dateBox" size="12" /></td>
					</tr>
					<tr>
						<td class="text">Possible Points: </td>
						<td> <input class="required" type="text" name="possibleBox" size="12" /></td>
					</tr>
					<tr>
						<td> <input name="AddAssignmentButton" type="submit" value="Add Assignment" /></td>
					</tr>
				</table>
			</form>
		</div>	
		
		<!-- Button and code goes here for uploading -->
		
		<div class="rightSidebar">
			<c:if test="${isStudent}">
				<c:if test="${enrolledCourses != null}">
					Enrolled Courses: <br/>
					<c:forEach var="course" items="${enrolledCourses}">
						<A HREF="scourse?id=${course.id}">${course.name}</A><br/>
					</c:forEach>
				</c:if>
			</c:if>
			<c:if test="${isProfessor}">
				<c:if test="${taughtCourses != null}">
					Taught Courses: <br/>
					<c:forEach var="course" items="${taughtCourses}">
						<A HREF="pcourse?id=${course.id}">${course.name}</A><br/>
					</c:forEach>
				</c:if>
			</c:if>
			<c:if test="${isStudent}">
				Upcoming Assignments: <br />
				<c:if test="${upcomingAssignments == null}">
					No upcoming assignments.<br />
				</c:if>
				<c:if test="${upcomingAssignments != null}">
					<c:forEach var="assignment" items="${upcomingAssignments}">
						<b>${assignment.key.name}</b> <br />
						Class: ${assignment.value} <br />
						Due Date: ${assignment.key.dueDate} <p />
					</c:forEach>
				</c:if>
			</c:if>
			<p />
			<A HREF="addCourse">Add Course</A><br/>
			<A HREF="changeUserType">Change User Type</A><br/>
		</div>
		<div class="footer">Random Copyright info goes here</div>
				
	</body>
	
</html>