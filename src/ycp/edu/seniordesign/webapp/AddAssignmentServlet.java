package ycp.edu.seniordesign.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.AssignmentController;
import ycp.edu.seniordesign.model.User;

public class AddAssignmentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// User must be logged in to access this page
		if (req.getSession().getAttribute("user") == null){
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/view/addAssignment.jsp").forward(req, resp);
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
				//String Date = req.getParameter("dateBox");
				int Possible = Integer.parseInt(req.getParameter("possibleBox"));
				
				//Update Database
				AssignmentController controller = new AssignmentController();
				
				try 
				{
					controller.CreateAssignment(userID, courseID);
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
