package ycp.edu.seniordesign.webapp.admin2;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.admin.AdminApproveCourseController;
import ycp.edu.seniordesign.model.Course;

public class AdminApproveCourseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// Must be logged in as an admin to view this page
		if (req.getSession().getAttribute("admin") == null){
			getServletContext().getRequestDispatcher("/view/admin/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/view/admin/approveCourse.jsp").forward(req, resp);
		}	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// Must be logged in as an admin to view this page
		if (req.getSession().getAttribute("admin") == null){
			getServletContext().getRequestDispatcher("/view/admin/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/view/admin/approveCourse.jsp").forward(req, resp);
		}	
		
		AdminApproveCourseController controller = new AdminApproveCourseController();
		
		if (req.getParameter("addCourseButton") != null){
			Course course = setCourse(req);
			try {
				controller.addCourse(course);
				req.setAttribute("updateMessage", "Successfully added course: " + course.getName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		getServletContext().getRequestDispatcher("/view/admin/approveCourse.jsp").forward(req, resp);
	}
	
	private Course setCourse(HttpServletRequest req){
		Course course = new Course();
		course.setName(req.getParameter("courseName"));
		course.setProfessorId(Integer.parseInt(req.getParameter("professorId")));
		course.setTime(req.getParameter("time"));
		course.setCourseNumber(Integer.parseInt(req.getParameter("courseNumber")));
		course.setSectionNumber(Integer.parseInt(req.getParameter("sectionNumber")));
		course.setCredits(Integer.parseInt(req.getParameter("credits")));
		course.setDays(req.getParameter("days"));
		course.setLocation(req.getParameter("location"));
		course.setCRN(Integer.parseInt(req.getParameter("CRN")));
		course.setDescription(req.getParameter("description"));
		return course;
	}
}
