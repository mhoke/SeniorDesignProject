package ycp.edu.seniordesign.webapp;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.GradebookController;
import ycp.edu.seniordesign.model.Assignment;
import ycp.edu.seniordesign.model.User;

public class ProfessorGradebookServlet extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getSession().getAttribute("user") == null)
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
		}
		
		else
		{
			User user = (User) req.getSession().getAttribute("user");
			
			GradebookController controller = new GradebookController();
			
			controller.setModel(user);
			resp.setContentType("application/json");
			
			int courseID = -1;
			courseID = Integer.parseInt(req.getQueryString().split("=")[1]);
			
			if(courseID != -1)
			{
				try 
				{
					req.getSession().setAttribute("course", controller.getCourse(courseID));
					ArrayList<Assignment> temp = controller.getProfessorAssignments(courseID);
					req.getSession().setAttribute("assignments", temp);
					req.getSession().setAttribute("names", controller.linkStudentNames(temp));
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			req.getRequestDispatcher("/view/professorGradebook.jsp").forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getParameter("updateGrades") != null)
		{
			ArrayList<Assignment> assignments = (ArrayList<Assignment>) req.getSession().getAttribute("assignments");
			int count = (int) req.getSession().getAttribute("counter");
			GradebookController controller = new GradebookController();
			
			boolean flag;
			
			for(int i = 0; i < count; i ++)
			{
				flag = false;
				Assignment assign = assignments.get(i);
				if(assign.getGradeWeightType() != Integer.parseInt(req.getParameter("Weight".concat("" + i))))
				{
					assign.setGradeWeightType(Integer.parseInt(req.getParameter("Weight".concat("" + i))));
					flag = true;
				}
				if(assign.getEarnedPoints() != Integer.parseInt(req.getParameter("Earned".concat("" + i))))
				{
					assign.setEarnedPoints(Integer.parseInt(req.getParameter("Earned".concat("" + i))));
					flag = true;	
				}
				if(assign.getPossiblePoints() != Integer.parseInt(req.getParameter("Possible".concat("" + i))))
				{
					assign.setPossiblePoints(Integer.parseInt(req.getParameter("Possible".concat("" + i))));
					flag = true;
				}
				
				if(flag)
				{
					try 
					{
						controller.updateAssignment(assign);
						req.getSession().setAttribute("errorMessage", "Update Successful");
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			req.getRequestDispatcher("/view/professorGradebook.jsp").forward(req, resp);
		}
	}
}
