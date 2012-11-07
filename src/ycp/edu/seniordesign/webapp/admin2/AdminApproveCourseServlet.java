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
			AdminApproveCourseController controller = new AdminApproveCourseController();
			
			if (req.getParameter("addCourseButton") != null){
				try {
					 Course course = controller.setCourse(req);
					if (course == null){
						req.setAttribute("errorMessage", "Invalid professor credentials");
					} else {
						controller.addCourse(course);
						req.setAttribute("updateMessage", "Successfully added course: " + course.getName());
					}
				} catch (SQLException e) {
					e.printStackTrace();
					req.setAttribute("errorMessage", "Failed to add course");
				}
			}
			
			getServletContext().getRequestDispatcher("/view/admin/approveCourse.jsp").forward(req, resp);
		}
	}
}
