<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<title>Home</title>
	</head>
	
	<body>
		<div class="header">${user.username}&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="Home.jsp">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;Logout</div>
		<div class="leftSidebar">Bus Schedule <br/> Campus Map</div>
		<div class="body">${user.username} <br/> ${user.emailAddress}</div>
		<div class="rightSidebar"><c:forEach var="course" items="${courses}">${course.name} <br/> </c:forEach></div>
	</body>
