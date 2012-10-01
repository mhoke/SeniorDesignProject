package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ycp.edu.seniordesign.model.User;

public class UserTest {

	User user = new User(1,"msteppe","msteppe@ycp.edu","password","salt",0);
	User user2 = new User(1,"msteppe","msteppe@ycp.edu","password","salt",2);
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertFalse(user.equals(user2));
		assertEquals(user.getId(),1);
		assertEquals(user.getUsername(),"msteppe");
		assertEquals(user.getEmailAddress(),"msteppe@ycp.edu");
		assertEquals(user.getPassword(),"password");
		assertEquals(user.getSalt(),"salt");
		assertEquals(user.getType(),0);
		
		user.setId(2);
		user.setUsername("nbrady");
		user.setEmailAddress("nbrady@ycp.edu");
		user.setPassword("incorrect");
		user.setSalt("saltier");
		user.setType(1);
		
		assertEquals(user.getId(),2);
		assertEquals(user.getUsername(),"nbrady");
		assertEquals(user.getEmailAddress(),"nbrady@ycp.edu");
		assertEquals(user.getPassword(),"incorrect");
		assertEquals(user.getSalt(),"saltier");
		assertEquals(user.getType(),1);
		assertTrue(user.equals(user));		
	}

}
