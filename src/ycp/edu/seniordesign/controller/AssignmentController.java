package ycp.edu.seniordesign.controller;

import java.sql.SQLException;
import java.util.Date;

import ycp.edu.seniordesign.model.persist.Database;

public class AssignmentController 
{
	public void CreateAssignment(int userID, int courseID, String name, Date date, int possible) throws SQLException
	{
		Database.getInstance().createAssignment(userID, courseID, name, date, possible);
	}
}
