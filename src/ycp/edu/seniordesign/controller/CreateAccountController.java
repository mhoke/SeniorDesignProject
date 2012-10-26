package ycp.edu.seniordesign.controller;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.PersistenceException;
import ycp.edu.seniordesign.model.persist.Database;

public class CreateAccountController 
{
	private User user;
	
	public void setModel(User model)
	{
		this.user = model;
	}
	
	public int createAccount(String username, String name, String password, String email) throws SQLException, PersistenceException
	{
		System.out.println("Attempts to create an account");
		return Database.getInstance().createAccount(username, name, password, email, User.STUDENT_PROFILE);
	}
}

