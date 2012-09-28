package ycp.edu.seniordesign.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.util.HashPassword;


public class Database {
	
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
			connection = DriverManager.getConnection("jdbc:hsqlbd:newBB.db");		
			
			// look up user with the given username
			statement = connection.prepareStatement("select * from newPi.users where username=?");
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// the user does exist
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
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	/**
	 * Create an account in the user table
	 * @param username the username of the new account
	 * @param password the plain-text password of the new account
	 * @param emailAddress the emailAddress of the new account
	 * @param type the type of the new account (1 for student, 2 for professor)
	 * @return false if a username with the username already exists, true if the account is successfully created
	 * @throws SQLException
	 */
	public boolean createAccount(String username, String password, String emailAddress, int type) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:hsqlbd:newBB.db");
						
			// check to see if username is taken
			statement = connection.prepareStatement("select * from newPi.users where username=?");
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
			statement = connection.prepareStatement("insert into newPi.users values(NULL,?,?,?,?,?)");
			statement.setString(1, username);
			statement.setString(2, emailAddress);
			statement.setString(3, hashedPassword);
			statement.setString(4, salt);
			statement.setInt(5, type);
			statement.execute();
			
			return true;
		} finally {
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
			connection = DriverManager.getConnection("jdbc:hsqlbd:newPi.db");
				
			// TODO: hash password
			
			// check to see if user exists
			statement = connection.prepareStatement("select * from newPi.users where username=? and password=?");
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			 
			if (resultSet.next()){
				// the users exists
				// delete the user from the database
				statement = connection.prepareStatement("delete from newPi.users where username=? and password=?");
				statement.setString(1,  username);
				statement.setString(2, password);
				statement.execute();
				
				return true;
			} else {
				// the user does not exist
				return false;
			}

		} finally {
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
			connection = DriverManager.getConnection("jdbc:hsqlbd:newPi.db");
						
			statement = connection.prepareStatement("select * from newPi.users where id=?");
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
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
	
	public Course getCourseById(int id) throws SQLException{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:hsqlbd:newPi.db");
						
			statement = connection.prepareStatement("select * from newPi.courses where id=?");
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
			DBUtil.closeQuietly(statement);
			DBUtil.closeQuietly(resultSet);
		}
	}
}
