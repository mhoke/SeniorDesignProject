<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="Skeleton.jsp" %>

<html>

	<head>
		<Title>Gradebook</Title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
		
		<script type="text/javascript">
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
						url: '/Whiteboard/scourse',
						data: {id: ''+id},
						success:
							function(data, textStatus, jqXHR)
							{
								location.href = '/Whiteboard/scourse';
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
						<td>${assign.name}</td>
						<td>${assign.earnedPoints}</td>
						<td>${assign.possiblePoints}</td>
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

</html>