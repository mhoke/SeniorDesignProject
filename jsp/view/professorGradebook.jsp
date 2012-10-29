<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Gradebook</Title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		
		<script>
		var id;
		
		$(document).ready(			
		function()
		{
			var whole = window.location.search.substring(1);
			var pair = whole.split("=");
			
			if(pair[0] == 'id')
			{				
				id = pair[1];
				
				$.ajax({
					type: 'POST',
					url: '/Whiteboard/pcourse',
					data: {id: ''+id},
					success:
						function(data, textStatus, jqXHR)
						{
							location.href = '/Whiteboard/pcourse';
						},
					error:
						function(jqXHR, textStatus, errorThrown)
						{
							alert("We are hosed");
						},
					dataType: 'json'
				});
			}
		});
		</script>
		
		<script>
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
					<td align="center">${course.name}</td>
				</tr>
				<c:forEach var="assign" items="${assignments}">
					<tr>
						<td><button id="${assign.name}">${assign.name}</button></td>
					</tr>
					<tr>
						<td>					
							<table id="${assign.name}" border="1">
								<tr>
									<td align="center">Student Name</td>
									<td align="center">Due Date</td>
									<td align="center">Weight</td>
									<td align="center">Earned Points</td>
									<td align="center">Possible Points</td>
								</tr>
								<c:forEach var="name" items="${names}">
									<tr>
										<td align="center">Name will go here</td>
										<td align="center">${assign.dueDate}</td>
										<td align="center">${assign.gradeWeightType}</td>
										<td align="center">${assign.earnedPoints}</td>
										<td align="center">${assign.possiblePoints}</td>
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