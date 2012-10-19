package ycp.edu.seniordesign.controller.admin;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.persist.Database;

public class AdminApproveCourseController {
	public int addCourse(Course course) throws SQLException{
		return Database.getInstance().addCourseForProfessor(course);
	}
}
