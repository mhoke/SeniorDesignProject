package ycp.edu.seniordesign.webapp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.AssignmentController;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class AddAssignmentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// User must be logged in to access this page
		if (req.getSession().getAttribute("user") == null)
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		} 
		else 
		{
			int courseID = -1;
			if(req.getQueryString() != null && req.getQueryString().contains("id="))
			{
				courseID = Integer.parseInt(req.getQueryString().split("=")[1]);
			}
			
			if(courseID != -1)
			{
				try 
				{
					req.setAttribute("ListofGrades", Database.getInstance().getGradesforCourse(courseID));
					req.getRequestDispatcher("/view/addAssignment.jsp").forward(req, resp);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if (req.getSession().getAttribute("user") == null)
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		} 
		else 
		{
			int courseID = (int) req.getSession().getAttribute("course");
			int userID = ((User) req.getSession().getAttribute("user")).getId();
			
			//Get text fields
			if(req.getParameter("AddAssignmentButton") != null)
			{
				String Name = req.getParameter("nameBox");
				int Year = Integer.parseInt(req.getParameter("yearBox"));
				int Month = Integer.parseInt(req.getParameter("monthBox"));
				int Day = Integer.parseInt(req.getParameter("dayBox"));
				int Possible = Integer.parseInt(req.getParameter("possibleBox"));
				
				Date date = new Date(Year - 1900, Month, Day);
				
				//Update Database
				AssignmentController controller = new AssignmentController();
				
				try 
				{
					controller.CreateAssignment(userID, courseID, Name, date, Possible);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			/*Handler for if upload file is pressed
			 * else if(req.getParameter("UploadButton") != null)
			 */
		}
	}
}
