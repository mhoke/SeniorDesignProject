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
	
	public Boolean createAccount(String username, String password, String email) throws SQLException, PersistenceException
	{
		System.out.println("Attempts to create an account");
		return Database.getInstance().createAccount(username, password, email, user.STUDENT_PROFILE);
	}
}

