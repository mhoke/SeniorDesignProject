package ycp.edu.seniordesign.main;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.ComputeGrade;
import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class Main {
	public static void main(String[] args) throws SQLException {
		User student = new User();
		student.setId(0);
		Course course = new Course();
		course.setId(0);
		ComputeGrade computeGrade = new ComputeGrade();
		computeGrade.setCourse(course);
		computeGrade.setStudent(student);
		computeGrade.computeScore();
	}
		
}
