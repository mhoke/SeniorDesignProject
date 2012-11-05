package ycp.edu.seniordesign.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

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
	
	public ArrayList<Assignment> getProfessorAssignments(int id) throws SQLException
	{
		return Database.getInstance().getAssignmentsForProfessor(id);
	}
	
	public TreeMap<Integer, String> linkStudentNames(ArrayList<Assignment> assignments) throws SQLException
	{
		TreeMap<Integer, String> returnMap = new TreeMap<Integer, String>();
		
		for(Assignment a : assignments)
		{
			String name = Database.getInstance().getUserById(a.getStudentId()).getName();
			if(!returnMap.containsKey(a.getStudentId()))
			{
				returnMap.put(a.getStudentId(), name);
			}
		}
		
		return returnMap;
	}
	
	public ArrayList<Assignment> getInstancesofAssignment(int id) throws SQLException
	{
		return Database.getInstance().getInstancesofAssignment(id);
	}
	
	public void updateAssignment(Assignment assign) throws SQLException
	{
		Database.getInstance().updateAssignment(assign);
	}
}