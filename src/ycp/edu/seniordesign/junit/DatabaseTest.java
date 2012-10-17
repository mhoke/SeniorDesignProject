package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import ycp.edu.seniordesign.model.Assignment;
import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.EnrolledCourse;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class DatabaseTest {
	
	User testStudent = new User(1, "username", "password", "salt", "emailAddress", User.STUDENT_PROFILE, "CS", true);
	User testProfessor = new User(2, "testProfessor", "password", "salt", "emailAddress", User.PROFESSOR_PROFILE, "None", true);
	Course testCourse = new Course(1, "Calc", testProfessor.getId(), "8AM - 9AM", 320, 101, 4, "MWF", "KEC 119", 123456, "This is a math class.");
	EnrolledCourse testEnrolledCourse = new EnrolledCourse(1, testStudent.getId(), testProfessor.getId(), testCourse.getId(), 100);
	Assignment testAssignment = new Assignment(1, testCourse.getId(), testStudent.getId(), "Homework #1", new Date(112, 8, 1), 1, 20, 20);
	
	@Test
	// This method  tests operations associated with the users table (createAccount, authenticate user, etc.)
	public void testUserOperations() throws SQLException {
		assertTrue(Database.getInstance().createAccount(testStudent.getUsername(), testStudent.getPassword(), testStudent.getEmailAddress(), testStudent.getType()));
		assertTrue(Database.getInstance().authenticateUser(testStudent.getUsername(), testStudent.getPassword()) != null);
		assertFalse(Database.getInstance().createAccount(testStudent.getUsername(), testStudent.getPassword(), testStudent.getEmailAddress(), testStudent.getType()));
		assertTrue(Database.getInstance().deleteAccount(testStudent.getUsername(), testStudent.getPassword()));
	}
	
	@Test
	// This method tests operations associated with the enrolled_courses table (addEnrolledCourseForStudent, removeEnrolledCourseForStudent, etc.)
	public void testEnrolledCourseOperations() throws SQLException{
		int id = Database.getInstance().addEnrolledCourseForStudent(testStudent.getId(), testProfessor.getId(), testCourse.getId());
		testEnrolledCourse.setId(id);
		assertEquals(Database.getInstance().getEnrolledCourseById(id).getGrade(), 100);
		assertEquals(Database.getInstance().getEnrolledCoursesForStudent(testStudent).get(0), testEnrolledCourse);
		Database.getInstance().removeEnrolledCourseForStudent(testCourse.getId(), testStudent.getId());
		assertEquals(Database.getInstance().getEnrolledCourseById(testEnrolledCourse.getId()), null);		
	}
	
	@Test
	// This method tests operations associated with the course table
	public void testCourseOperations() throws SQLException{
		int id = Database.getInstance().addCourseForProfessor(testCourse);
		testCourse.setId(id);
		assertEquals(Database.getInstance().getCourseById(id), testCourse);
		assertEquals(Database.getInstance().getCoursesForProfessor(testProfessor).get(0), testCourse);
		Database.getInstance().removeCourseForProfessor(id);
		assertEquals(Database.getInstance().getCourseById(testCourse.getId()), null);
	}
	
	@Test
	// This method tests operations associated with the assignments table 
	public void testAssignmentOperations() throws SQLException{
		int id = Database.getInstance().addAssignmentForCourse(testAssignment);
		testAssignment.setId(id);
		assertEquals(Database.getInstance().getAssignmentById(id).getName(), testAssignment.getName());
		Database.getInstance().removeAssignmentForCourse(testAssignment.getId());
		assertEquals(Database.getInstance().getAssignmentById(id), null);
	}

}
