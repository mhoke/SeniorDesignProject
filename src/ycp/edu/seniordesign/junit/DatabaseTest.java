package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class DatabaseTest {

	// This method  tests the createAccount, deleteAccount, and authenticateUser methods in the database
	@Test
	public void testAccountOperations() throws SQLException {
		assertTrue(Database.getInstance().createAccount("username", "password", "test@whiteboard.org", User.PROFESSOR_PROFILE));
		assertTrue(Database.getInstance().authenticateUser("username", "password"));
		assertFalse(Database.getInstance().createAccount("username", "password", "test@whiteboard.org", User.PROFESSOR_PROFILE));
		assertTrue(Database.getInstance().deleteAccount("username", "password"));
	}
	
	//TODO: test the rest of database methods
}
