package ycp.edu.seniordesign.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import ycp.edu.seniordesign.model.Assignment;
import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.util.HashPassword;


public class Database {
	static {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Could not load hsql driver");
		}
	}

	private static final String JDBC_URL ="jdbc:hsqldb:file:whiteboard.db";
	
	private static final Database theInstance = new Database();
	
	public static Database getInstance() {
		return theInstance;
	}
	
	public Database(){

	}
	
	/**
	 * Authenticate the user via username and password
	 * @param username the username of the user trying to login
	 * @param password the plain-text password of the user trying to login
	 * @return true if the user exists, false otherwise
	 * @throws SQLException
	 */
	public boolean authenticateUser(String username, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);	
			
			// look up user with the given username
			statement = connection.prepareStatement("select * from users where username=?");
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// there is someone with the given username
				User user = new User();
				user.loadFrom(resultSet);
				
				// Check password
				String hashedPassword = HashPassword.computeHash(password, user.getSalt());
				if (hashedPassword.equals(user.getPassword())){
					// passwords matched
					return true;
				} else {
					// passwords did not match
					return false;
				}
			} else {
				// the user does not exist
				return false;
			}
				 	 
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * Create an account in the user table
	 * @param username the username of the new account
	 * @param password the plain-text password of the new account
	 * @param emailAddress the emailAddress of the new account
	 * @param type the type of the new account (1 for student, 2 for professor, 3 for both)
	 * @return false if a username with the username already exists, true if the account is successfully created
	 * @throws SQLException
	 */
	public boolean createAccount(String username, String password, String emailAddress, int type) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		// TODO: do not allow creation of account with a variation in case on a current username
		// Example: If there is a user with a user name of "username" do not allow creation of an account with the username of 
		// "UsErNaME"
		try {
			connection = DriverManager.getConnection(JDBC_URL);
						
			// check to see if username is taken
			statement = connection.prepareStatement("select * from users where username=?");
			statement.setString(1, username);
			
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// the username is already taken
				return false;
			}
			
			// generate random salt and hash password
			String salt = HashPassword.generateRandomSalt(new Random());
			String hashedPassword = HashPassword.computeHash(password, salt);
			
			// add the user to the database
			statement = connection.prepareStatement("insert into users values(NULL,?,?,?,?,?)");
			statement.setString(1, username);
			statement.setString(2, hashedPassword);
			statement.setString(3, salt);
			statement.setString(4, emailAddress);
			statement.setInt(5, type);
			statement.execute();
						
			return true;
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * Delete account from the user table
	 * @param username the username of the account to be deleted
	 * @param password the password of the account to be deleted
	 * @return ture if the account is sucessfully deleted, false otherwise
	 * @throws SQLException
	 */
	public boolean deleteAccount(String username, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
				
			// check to see if user exists
			statement = connection.prepareStatement("select * from users where username=?");
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// there is someone with the given username
				User user = new User();
				user.loadFrom(resultSet);
				
				// Check password
				String hashedPassword = HashPassword.computeHash(password, user.getSalt());
				if (hashedPassword.equals(user.getPassword())){
					// delete the user from the database
					statement = connection.prepareStatement("delete from users where username=? and password=?");
					statement.setString(1,  username);
					statement.setString(2, hashedPassword);
					statement.execute();
				
					return true;
				} else {
					// the user does not exist
					return false;
				}
			}
			
			return false;
		
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * Gets the user with the given id
	 * @param id the id of the user to search for
	 * @return the User object with the given id if it exists, otherwise null
	 * @throws SQLException
	 */
	public User getUserById(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
						
			statement = connection.prepareStatement("select * from users where id=?");
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// the user exists
				User user = new User();
				user.loadFrom(resultSet);
				return user;
			}
			
			else {
				// no user exist with the given id
				return null;
			}
			
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	public Course getCourseById(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
						
			statement = connection.prepareStatement("select * from courses where id=?");
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// the course exists
				Course course = new Course();
				course.loadFrom(resultSet);
				return course;
			}
			
			else {
				// no course exist with the given id
				return null;
			}
			
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);

		}
	}
	
	public Assignment getAssignmentById(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			
			statement = connection.prepareStatement("select * from assignments where id=?");
			statement.setInt(1,  id);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Assignment assignment = new Assignment();
				assignment.loadFrom(resultSet);
				return assignment;
			} else {
				return null;
			}
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * This method returns a list of all the courses taken by the student
	 * @param user
	 * @return if the user is a professor the method returns null, otherwise the method returns an ArrayList of all the courses the student is enrolled in
	 * @throws SQLException
	 */
	public ArrayList<Course> getCoursesForStudent(User user) throws SQLException{
		if (user.getType() == User.PROFESSOR_PROFILE){
			// the user that was passed is a professor and thus does not take any classes 
			return null;
		}
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("JDBC_URL");
						
			statement = connection.prepareStatement("select * from courses where student_id=?");
			statement.setInt(1, user.getId());
			resultSet = statement.executeQuery();
			
			ArrayList<Course> courses = new ArrayList<Course>(); 
			while (resultSet.next()){
				Course course = new Course();
				course.loadFrom(resultSet);
				courses.add(course);
			}
			
			return courses;
			
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * This method returns a list of all the courses taught by a professor
	 * @param user 
	 * @return if the user is a student the method returns null, otherwise the method returns an ArrayList of all the course the professor teaches
	 * @throws SQLException
	 */
	public ArrayList<Course> getCoursesForProfessor(User user) throws SQLException{
		if (user.getType() == User.STUDENT_PROFILE){
			// the user that was passed is a student and thus does not teach any classes
			return null;
		}
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("JDBC_URL");
						
			statement = connection.prepareStatement("select * from courses where professor_id=?");
			statement.setInt(1, user.getId());
			resultSet = statement.executeQuery();
			
			ArrayList<Course> courses = new ArrayList<Course>(); 
			while (resultSet.next()){
				Course course = new Course();
				course.loadFrom(resultSet);
				courses.add(course);
			}
			
			return courses;
			
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * This method gets a list of all assignments for the given courses and student
	 * @param courseId the courseId the assignment is for
	 * @param studentId the studentId the assignment is for
	 * @return an ArrayList of the assignments for the given couse and student
	 * @throws SQLException
	 */
	public ArrayList<Assignment> getAssignments(int courseId, int studentId) throws SQLException{		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("JDBC_URL");
						
			statement = connection.prepareStatement("select * from assignements where course_id=? and student_id=?");
			statement.setInt(1, courseId);
			statement.setInt(2,  studentId);
			resultSet = statement.executeQuery();
			
			ArrayList<Assignment> assignments = new ArrayList<Assignment>(); 
			while (resultSet.next()){
				Assignment assignment = new Assignment();
				assignment.loadFrom(resultSet);
				assignments.add(assignment);
			}
			
			return assignments;
			
		} finally {
			DBUtil.close(connection);
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
}
