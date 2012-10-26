<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Gradebook</Title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		
		<script>
			function onButtonPress()
			{
				$.ajax({
					
					
					
				});
			}
			
			function hide(var id)
			{
				document.getElementById('id').style.visibility = "hidden";
			}
			
			function show(var id)
			{
				document.getElementById('id').style.visibility = "visible";
			}
		</script>
		
	</head>
	
	<body>
		<div class="header"><A HREF="editProfile"> ${user.username}</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="home">Home</A>&emsp;&emsp;&emsp;&emsp;&emsp;<A HREF="login">Logout</A></div>
		<div class="leftSidebar">Bus Schedule <br/> Campus Map</div>
		<div class="body">
			<table>
				<tr>
					<td>${course.name}</td>
				</tr>
				<c:forEach var="assign" items="${assignments}">
					<tr>
						<td><button id="${assign.name}">${assign.name}</button></td>
					</tr>
					<tr>
						<td>					
							<table id="${assign.name}">
								<tr>
									<td>Student Name</td>
									<td>Due Date</td>
									<td>Weight</td>
									<td>Earned Points</td>
									<td>Possible Points</td>
								</tr>
								<c:forEach var="name" items="${names}">
									<tr>
										<td>${names.${assign.courseID}}</td>
										<td>${assign.dueDate}</td>
										<td>${assign.gradeWeightType}</td>
										<td>${assign.earnedPoints}</td>
										<td>${assign.possiblePoints}</td>
									</tr>	
								</c:forEach>
							</table>
						</td>
					</tr>
				</c:forEach>
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