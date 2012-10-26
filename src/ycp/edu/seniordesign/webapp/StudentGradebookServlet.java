package ycp.edu.seniordesign.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.GradebookController;
import ycp.edu.seniordesign.model.User;

public class StudentGradebookServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getSession().getAttribute("user") == null)
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
		}
		
		else
		{
			req.getRequestDispatcher("/view/studentGradebook.jsp").forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = (User) req.getSession().getAttribute("user");
		
		if(user == null)
		{
			req.getRequestDispatcher("/view/login.jsp");
		}
		GradebookController controller = new GradebookController();
		
		controller.setModel(user);
		resp.setContentType("application/json");
		
		if(!req.getParameter("id").equals("undefined"))
		{
			int courseID = Integer.parseInt(req.getParameter("id"));
			try 
			{
				req.getSession().setAttribute("course", controller.getCourse(courseID));
				req.getSession().setAttribute("assignments", controller.getStudentAssignments(courseID));
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
