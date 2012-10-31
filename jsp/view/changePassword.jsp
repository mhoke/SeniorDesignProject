<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Change Password</Title>
	</head>
	
	<body>
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar"> Bus Schedule <br/> Campus Map</div>
		<div class="body">
			<c:if test="${isStudent}">
				<c:if test="${!empty errorMessage }">
					<div class="error">${errorMessage}</div>
				</c:if>
							
				<form action="${pageContext.servletContext.contextPath}/changePassword" method="post">
					<table>
						<tr>
							<td class="text">Old Password: </td>
							<td> <input type="password" name="oldPasswordBox" size="12" value="${password}" /></td>
						</tr>
						
						<tr>
							<td class="text">New Password: </td>
							<td> <input type="password" name="newPasswordBox" size="12" value="${password}" /></td>
						</tr>
						
						<tr>
							<td class="text">Confirm New Password: </td>
							<td> <input type="password" name="confirmNewPasswordBox" size="12" value="${password}" /></td>
						</tr>
						
						<tr>
							<td> <input name="ChangePasswordButton" type="submit" value="Change Password" /></td>
						</tr>
					</table>
				</form>
			</c:if>	
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