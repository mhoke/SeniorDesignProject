package ycp.edu.seniordesign.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getSession().getAttribute("user") == null)
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher("/view/changePassword.jsp").forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if(req.getParameter("ChangePasswordButton") != null) 
		{
			try
			{
				
				
				req.getRequestDispatcher("/view/changePassword.jsp").forward(req, resp);
			}
			catch(Exception e)
			{
				System.out.println("Changing password failed");
				e.printStackTrace();
			}
		}
	}
}
