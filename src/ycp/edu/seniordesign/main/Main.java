package ycp.edu.seniordesign.main;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;
import ycp.edu.seniordesign.model.persist.PersistenceException;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException, PersistenceException {
		User user = new User();
		user.setUsername("Nick");
		user.setPassword("password");
		user.setEmailAddress("nbrady1@ycp.edu");
		user.setType(User.PROFESSOR_PROFILE);
		Database.getInstance().createAccount("Nick", "password", "nbrady1@ycp.edu", User.STUDENT_PROFILE);
		//Database.getInstance().createAccount(user);
		System.out.println("DONE");
	}
		
}
