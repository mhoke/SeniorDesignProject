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
	
	public Boolean createAccount(String username, String password, String email) throws PersistenceException, SQLException
	{
		return Database.getInstance().createAccount(user.getUsername(), user.getPassword(), user.getEmailAddress(), user.getType());
	}
	
	public Boolean login(String username, String password) throws SQLException, PersistenceException
	{
		return Database.getInstance().authenticateUser(user.getUsername(), user.getPassword());
	}
}
