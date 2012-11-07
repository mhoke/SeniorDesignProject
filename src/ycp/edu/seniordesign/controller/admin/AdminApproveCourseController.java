package ycp.edu.seniordesign.controller.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class AdminApproveCourseController {
	
	public int addCourse(Course course) throws SQLException{
		return Database.getInstance().addCourseForProfessor(course);
	}

	public Course setCourse(HttpServletRequest req) throws SQLException {
		// Look up the professor id for the given professor
		User user = Database.getInstance().getUserByEmail(req.getParameter("emailAddress"));
		if (user == null){
			// No such user
			return null;
		} else if (user.getId() == User.STUDENT_PROFILE){
			// The user found was a student, they are no allowed to create course
			return null;
		} else if (!user.getName().equals(req.getAttribute("professorName"))){
			// The name field for the user did not match what was given
			return null;
		}
		
		// Create the course object
		Course course = new Course();
		course.setName(req.getParameter("courseName"));
		course.setProfessorId(user.getId());
		course.setTime(req.getParameter("time"));
		course.setCourseNumber(Integer.parseInt(req.getParameter("courseNumber")));
		course.setSectionNumber(Integer.parseInt(req.getParameter("sectionNumber")));
		course.setCredits(Integer.parseInt(req.getParameter("credits")));
		course.setDays(req.getParameter("days"));
		course.setLocation(req.getParameter("location"));
		course.setCRN(Integer.parseInt(req.getParameter("CRN")));
		course.setDescription(req.getParameter("description"));
		return course;
		
	}
}
