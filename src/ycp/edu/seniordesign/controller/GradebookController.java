package ycp.edu.seniordesign.controller;

import java.sql.SQLException;

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
}