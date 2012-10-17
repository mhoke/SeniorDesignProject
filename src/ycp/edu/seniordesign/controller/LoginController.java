package ycp.edu.seniordesign.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.EnrolledCourse;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.PersistenceException;
import ycp.edu.seniordesign.model.persist.Database;

public class LoginController 
{
	private User user;
	
	public void setModel(User model)
	{
		this.user = model;
	}
	
	public User login(String username, String password) throws SQLException, PersistenceException
	{
		return Database.getInstance().authenticateUser(username, password);
	}
	
	public ArrayList<EnrolledCourse> getEnrolledCourses(User user) throws SQLException
	{
		ArrayList<EnrolledCourse> returnList = Database.getInstance().getEnrolledCoursesForStudent(user);
		
		return returnList;
	}
	
	public ArrayList<Course> getCourses(ArrayList<EnrolledCourse> courses) throws SQLException
	{
		ArrayList<Course> returnList = new ArrayList<Course>();
		
		for(EnrolledCourse c : courses)
		{
			returnList.add(Database.getInstance().getCourseById(c.getId()));
		}
		
		return returnList;
	}
}
