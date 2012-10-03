package ycp.edu.seniordesign.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ycp.edu.seniordesign.model.Assignment;

public class AssignmentTest {
	// TODO: use the new constructor and test the new fields
	Assignment assign = new Assignment(1, 1, 1, "Test 1", null, 50, 50, 50);
	Assignment assign2 = new Assignment(1, 1, 1, "Test 2", null, 50, 50, 50);

	@Test
	public void test() {
		//fail("Not yet implemented");
		assertFalse(assign.equals(assign2));
		assertEquals(assign.getId(),1);
		assertEquals(assign.getName(),"Test 1");
		assertEquals(assign.getGradeWeightType(),50);
		assertEquals(assign.getCourseId(),1);
		
		assign.setId(2);
		assign.setName("Quiz 1");
		assign.setGradeWeightType(25);
		assign.setCourseId(2);
		
		assertEquals(assign.getId(),2);
		assertEquals(assign.getName(),"Quiz 1");
		assertEquals(assign.getGradeWeightType(),25);
		assertEquals(assign.getCourseId(),2);
		assertTrue(assign.equals(assign));
	}
}
