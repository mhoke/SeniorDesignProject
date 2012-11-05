<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

	<head>
		<Title>Change Password</Title>
		
		<head>		
		<style type="text/css">
		
			.error
			{
				color: red;
			}
			
			.text
			{
				color: black;
			}
			
			.header
			{
				width:80%;
				height:10%;
				clear:both;
				text-align:center;
				background-color:#C2D6D6;
			}
			
			.leftSidebar
			{
				width:20%;
				height:80%;
				float:left;
				text-align:center;
				background-color:#FF0000;
			}
			
			.body
			{
				width:40%;
				height:80%;
				overflow:auto;
				float:left;
				text-align:center;
				background-color:#00FF00;
			}
			
			.rightSidebar
			{
				width:20%;
				height:80%;
				float:left;
				text-align:center;
				background-color:#FFFFFF;
			}
			
			.footer
			{
				color:red;
				width:80%;
				height:10%;
				clear:both;
				text-align:center;
				background-color:#000000;
			}
		</style>
	</head>
	
	<body background = "https://cas.ycp.edu/cas/images/cas-bg.jpg">
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar">Bus Schedule <br/> Campus Map</div>
		<div class="body">
			<c:if test="${isStudent}">
				<c:if test="${!empty errorMessage }">
					<div class="error"><em>${errorMessage}</em></div>
				</c:if>
				
				<div class = "text"><font face="new york"><strong>CHANGE PASSWORD</strong></font></div>
				
				<hr noshade size=3> 
							
				<form action="${pageContext.servletContext.contextPath}/changePassword" method="post">
					<table BORDER="3" CELLPADDING="2" CELLSPACING="0" WIDTH=50% BGCOLOR="#00FF00" ALIGN="center"> 					
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
						
						
					</table>
					
					<hr noshade size=3>
					
					<td> <input name="ChangePasswordButton" type="submit" value="Change Password" /></td>
					
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