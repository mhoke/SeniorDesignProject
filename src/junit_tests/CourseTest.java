package junit_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ycp.edu.seniordesign.model.Course;

public class CourseTest {

	Course course = new Course(902751410, "Calc", "8AM - 9AM", 320, 101, 4, "MWF", "Kinsley 119", 123456, "This is a math class.");
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		assertEquals(course.getId(),902751410);
		assertEquals(course.getName(),"Calc");
		assertEquals(course.getTime(),"8AM - 9AM");
		assertEquals(course.getCourseNumber(),320);
		assertEquals(course.getSectionNumber(),101);
		assertEquals(course.getCredits(),4);
		assertEquals(course.getDays(),"MWF");
		assertEquals(course.getLocation(),"Kinsley 119");
		assertEquals(course.getCRN(),123456);
		assertEquals(course.getDescription(),"This is a math class.");
		assertTrue(course.equals(course));
		
		course.setId(902751411);
		assertEquals(course.getId(),902751411);
		course.setName("Calc 2");
		assertEquals(course.getName(),"Calc 2");
		
		
		
		
		
	}

	
	
	
}
