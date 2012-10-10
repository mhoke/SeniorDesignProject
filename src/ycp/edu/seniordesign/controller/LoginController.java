package ycp.edu.seniordesign.controller;

import java.sql.SQLException;

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
}
