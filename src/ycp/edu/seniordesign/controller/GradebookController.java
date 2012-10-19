package ycp.edu.seniordesign.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import ycp.edu.seniordesign.model.Assignment;
import ycp.edu.seniordesign.model.Course;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.PersistenceException;
import ycp.edu.seniordesign.model.persist.Database;

public class GradebookController 
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
	
	public Course getCourse(int id) throws SQLException
	{
		return Database.getInstance().getCourseById(id);
	}
	
	public ArrayList<Assignment> getStudentAssignments (int id) throws SQLException
	{
		return Database.getInstance().getAssignmentsForCourse(id, user.getId());
	}
}