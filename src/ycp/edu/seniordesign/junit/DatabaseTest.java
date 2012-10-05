package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import ycp.edu.seniordesign.model.EnrolledCourse;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class DatabaseTest {

	@Test
	// This method  tests operations associated with the users table (createAccount, authenticate user, etc.)
	public void testUserOperations() throws SQLException {
		assertTrue(Database.getInstance().createAccount("username", "password", "test@whiteboard.org", User.PROFESSOR_PROFILE));
		assertTrue(Database.getInstance().authenticateUser("username", "password"));
		assertFalse(Database.getInstance().createAccount("username", "password", "test@whiteboard.org", User.PROFESSOR_PROFILE));
		assertTrue(Database.getInstance().deleteAccount("username", "password"));
	}
	
	
	@Test
	// This method tests operations associated with the enrolled_courses table (addEnrolledCourseForStudent, removeEnrolledCourseForStudent, etc.)
	public void testEnrolledCourseOperations() throws SQLException{
		User testStudent = new User(999999, "username", "password", "salt", "emailAddress", User.STUDENT_PROFILE);
		int id;
		id = Database.getInstance().addEnrolledCourseForStudent(999999, 999999, 999999);
		EnrolledCourse testCourse = new EnrolledCourse(id, 999999, 999999, 999999, 100);
		assertEquals(Database.getInstance().getEnrolledCourseById(id).getGrade(), 100);
		assertEquals(Database.getInstance().getEnrolledCoursesForStudent(testStudent).get(0), testCourse);
		Database.getInstance().removeEnrolledCourseForStudent(999999, 999999);
		assertEquals(Database.getInstance().getEnrolledCourseById(999999), null);
	}
	
	@Test
	public void testCourseOperations(){
		
	}
	
	//TODO: test the rest of database methods

}
