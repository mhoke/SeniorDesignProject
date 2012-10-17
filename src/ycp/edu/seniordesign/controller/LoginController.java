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
	
	public User getModel()
	{
		return user;
	}
	
	public User login(String username, String password) throws SQLException, PersistenceException
	{
		return Database.getInstance().authenticateUser(username, password);
	}
	
	public ArrayList<Course> getEnrolledCourses() throws SQLException
	{
		ArrayList<EnrolledCourse> resultList = Database.getInstance().getEnrolledCoursesForStudent(user);
		
		if(resultList == null)
		{
			return null;
		}
		
		ArrayList<Course> returnList = new ArrayList<Course>();
		
		for(EnrolledCourse c : resultList)
		{
			returnList.add(Database.getInstance().getCourseById(c.getCourseId()));
		}
		
		return returnList;
	}
	
	public ArrayList<Course> getCourses() throws SQLException
	{		
		return Database.getInstance().getCoursesForProfessor(user);
	}
}
