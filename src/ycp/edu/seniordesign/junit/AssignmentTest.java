package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ycp.edu.seniordesign.model.Assignment;

public class AssignmentTest {
	
	Assignment assign = new Assignment(1, "Test 1", 50, 1);
	Assignment assign2 = new Assignment(1, "Test 2", 50, 1);

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertFalse(assign.equals(assign2));
		assertEquals(assign.getId(),1);
		assertEquals(assign.getName(),"Test 1");
		assertEquals(assign.getWeight(),50);
		assertEquals(assign.getCourseId(),1);
		
		assign.setId(2);
		assign.setName("Quiz 1");
		assign.setWeight(25);
		assign.setCourseId(2);
		
		assertEquals(assign.getId(),2);
		assertEquals(assign.getName(),"Quiz 1");
		assertEquals(assign.getWeight(),25);
		assertEquals(assign.getCourseId(),2);
		assertTrue(assign.equals(assign));
	}
}
