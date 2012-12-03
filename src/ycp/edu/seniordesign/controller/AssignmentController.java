package ycp.edu.seniordesign.controller;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.persist.Database;

public class AssignmentController 
{
	public void CreateAssignment(int userID, int courseID) throws SQLException
	{
		Database.getInstance().createAssignment(userID, courseID);
	}
}
