<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<title>Home</title>
	</head>
	
	<body>
		<div class="header">${user.username}&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar">Bus Schedule <br/> Campus Map</div>
		<div class="body">${user.username} <br/> ${user.emailAddress}</div>
		<div class="rightSidebar">
			<c:if test="${isStudent}">
				Enrolled Courses: <br/>
				<c:forEach var="course" items="${enrolledCourses}">
					<A HREF="course?id=${course.id}">${course.name}</A><br/>
				</c:forEach>
			</c:if>
			<c:if test="${isProfessor}">
				Taught Courses: <br/>
				<c:forEach var="course" items="${taughtCourses}">
					<A HREF="course?id=${course.id}">${course.name}</A><br/>
				</c:forEach>
			</c:if>
		</div>
		<div class="footer">Random Copyright info goes here</div>
	</body>
</html>
