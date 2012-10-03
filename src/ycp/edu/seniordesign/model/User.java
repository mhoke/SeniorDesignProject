package ycp.edu.seniordesign.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	/**
	 * Profile type for students
	 */
	public static final int STUDENT_PROFILE = 1;
	
	/**
	 * Profile type for professors
	 */
	public static final int PROFESSOR_PROFILE = 2;
	/**
	 * Profile type for professor and students
	 */
	public static final int PROFESSOR_STUDENT_PROFILE = 3;
	
	private int id;
	private String username;
	private String emailAddress;
	private String password; // this password is encrypted
	private String salt;
	private int type;
	
	public User(){
		
	}
	
	public User(int id, String username, String emailAddress, String password, String salt, int type){

		this.id = id;
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.salt = salt;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getPassword() {
		return password;
	}
	
	// NOTE: YOU SHOULD PASS THIS METHOD A HASHED PASSWORD
	// TODO: hash the password in this method
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt(){
		return salt;
	}
	
	public void setSalt(String salt){
		this.salt = salt;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * This method can be used to load the fields of a user from a resultSet to a User object
	 * @param resultSet the resultSet to load the fields from
	 * @throws SQLException
	 */
	public void loadFrom(ResultSet resultSet) throws SQLException {
		int index = 1;
		setId(resultSet.getInt(index++));
		setUsername (resultSet.getString(index++));
		setPassword(resultSet.getString(index++));
		setSalt(resultSet.getString(index++));
		setEmailAddress(resultSet.getString(index++));
		setType(resultSet.getInt(index++));
	}
	
	/**
	 * This method can be used to store the fields from a user object to a prepared statement
	 * @param statement the PreparedStatement to store the fields to
	 * @throws SQLException
	 */
	
	public void storeTo(PreparedStatement statement) throws SQLException {
		int index = 1;
		statement.setInt(index++, id);
		statement.setString(index++, username);
		statement.setString(index++, password);
		statement.setString(index++, salt);
		statement.setString(index++, emailAddress);
		statement.setInt(index++, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		User other = (User) obj;
		return id == other.id
			&& username.equals(other.username)
			&& password.equals(other.password)
			&& salt.equals(other.salt)
			&& emailAddress.equals(other.emailAddress)
			&& type == other.type;
	}

}
