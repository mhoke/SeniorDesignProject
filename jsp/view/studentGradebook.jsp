<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Gradebook</Title>
	</head>
	
	<body>
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar">Bus Schedule <br/><A HREF="campusMap">Campus Map</A></div>
		<div class="body">
			<div style="text-align:center">${course.name}</div>
			<br/>
			<table border="1" style="text-align:center; margin-left:auto; margin-right:auto; empty-cells:hide;">
				<tr>
					<td>Assignment</td>
					<td>Due Date</td>
					<td>Earned Points</td>
					<td>Possible Points</td>
				</tr>
				<c:forEach var="assign" items="${assignments}">
					<tr>
						<td>${assign.name}</td>
						<td>${assign.dueDate}</td>
						<td align="center">${assign.earnedPoints}</td>
						<td align="center">${assign.possiblePoints}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3" align="center">Course Average: </td>
					<td>${grade}</td>
				</tr>
			</table>
		</div>
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
		</div>
		<div class="footer">Random Copyright info goes here</div>
	</body>

</html>