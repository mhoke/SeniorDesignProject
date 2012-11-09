<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ycp.edu.seniordesign.model.Assignment" %>

<html>

	<head>
		<Title>Gradebook</Title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>	
	</head>
	
	<body>
		<script type="text/javascript">
			function Switch(id)
			{
				var v = document.getElementById(id);
				if(v.style.display == 'block')
				{
					v.style.display = 'none';	
				}
				else
				{
					v.style.display = 'block';
				}
			}
		</script>
			
		<% int counter = 0; %>
	
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar">Bus Schedule <br/><A HREF="campusMap">Campus Map</A></div>
		<div class="body">
			<div align="center">${course.name}</div>
			<c:if test="${!empty errorMessage }">
					<div class="error" align="left">${errorMessage}</div>
				</c:if>
			<form action="${pageContext.servletContext.contextPath}/pcourse" method="post">
				<table>
					<c:forEach var="assign" items="${assignments}">
						<tr>
							<td><button type="button" onclick="Switch(<%=counter%>)" id="${assign.name}">${assign.name}</button></td>
						</tr>
						<tr>
							<td>					
								<table id="<%=counter%>" border="1" style="text-align:center; display:none;">
									<tr>
										<td align="center">Student Name</td>
										<td align="center">Due Date</td>
										<td align="center">Weight</td>
										<td align="center">Earned Points</td>
										<td align="center">Possible Points</td>
									</tr>
									<% int idCount = 0; %>
									<c:forEach var="name" items="${names}">
										<% 
											ArrayList<Integer> temp = (ArrayList<Integer>) request.getSession().getAttribute("idList");
											int curVal = temp.get(idCount); 
											ArrayList<Assignment> a = (ArrayList<Assignment>) request.getSession().getAttribute("Values"+curVal);
											Assignment assignment = a.get(counter);
											request.setAttribute("assignment", assignment);
										%>
										<tr>
											<td align="center">${name}</td>
											<td align="center">${assign.dueDate}</td>
											<td><input type="text" name="Weight<%=counter%>" size="12" value="${assignment.gradeWeightType}" /></td>
											<td><input type="text" name="Earned<%=counter%>" size="12" value="${assignment.earnedPoints}" /></td>
											<td><input type="text" name="Possible<%=counter%>" size="12" value="${assignment.possiblePoints}" /></td>
										</tr>
										<% idCount ++; %>
									</c:forEach>
									<tr>
										<c:forEach var="i" items="${Grades}">
											<c:if test="${i.key == assign.name}">
												<td colspan="4" align="center">Class Average: </td>
												<td>${i.value}%</td>
											</c:if>
										</c:forEach>
									</tr>
								</table>
							</td>
						</tr>
						<% counter ++; %>
					</c:forEach>
					<% request.getSession().setAttribute("counter", counter); %>
				</table>
				<div align="right"><input name="updateGrades" type="submit" value="Update Grades"/></div>
			</form>
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