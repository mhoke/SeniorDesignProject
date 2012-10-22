<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Edit Profile</Title>
	</head>
	
	<body>
		<div class="header">${user.username}&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="editProfile">Edit Profile</A></div>
		<div class="leftSidebar"><A HREF="home">Home</A> <br/> Bus Schedule <br/> Campus Map</div>
		<div class="body">
			<c:if test="${isStudent}">
				UserName: ${user.username} <br/>
				Current Email Address: ${user.emailAddress} <br/>
				<c:if test="${user.commuter}">Current Status: Commuter</c:if><c:if test="!${user.commuter}">Current Status: Resident</c:if> <br/>
				Current Major: ${user.major} <br/>				
				
				<form action="${pageContext.servletContext.contextPath}/editProfile" method="post">
					<table>
						<tr>
							<td class="text">New Email Address: </td>
							<td> <input type="text" name="newEmailAddressBox" size="12" value="${user.emailAddress}" /></td>
						</tr>
						
						<tr>
							<td class="text">New Status: </td>
							<td> <input type="text" name="newStatusBox" size="12" value="${user.commuter}" /></td>
						</tr>
						
						<tr>
							<td class="text">New Major: </td>
							<td> <input type="text" name="newMajorBox" size="12" value="${user.major}" /></td>
						</tr>
						
						<tr>
							<td> <input name="ChangeFieldsButton" type="submit" value="Edit Profile" /></td>
							<td> <input name="ChangePasswordButton" type="submit" value="Change Password" /></td>
						</tr>
						
						<tr>
							<td class="error">${user.message}</td>
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

		
		