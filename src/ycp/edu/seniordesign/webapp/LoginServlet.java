package ycp.edu.seniordesign.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.LoginController;
import ycp.edu.seniordesign.model.User;

public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = new User();
		
		LoginController controller = new LoginController();
		
		controller.setModel(user);
		
		String username = req.getParameter("usernameBox");
		String password = req.getParameter("passwordBox");
		String email = req.getParameter("emailBox");
		
		String errorMessage = null;
		
		boolean result = false;
		
		if(req.getParameter("loginButton") != null)
		{
			try
			{
				result = controller.login(username, password);
				
				if(!result)
				{
					errorMessage = "Login failed";
				}
				else
				{
					errorMessage = "Login successful";
					req.getSession().setAttribute("user", user);
				}
			}
			catch(Exception e)
			{
				System.out.println("Login database fail");
			}
		}
		
		else if(req.getParameter("createButton") != null)
		{
			try
			{
				result = controller.createAccount(username, password, email);
				
				if(!result)
				{
					errorMessage = "Account already exists";
				}
				else
				{
					errorMessage = "Account successfully created";
				}
			}
			catch (Exception e)
			{
				System.out.println("Create database fail");
			}
		}
		
		req.setAttribute("errorMessage", errorMessage);
		
		if(result)
		{
			//Moves the page forward
			//req.getRequestDispatcher("/view/homePage.jsp").forward(req,resp);
		}
		else
		{
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}
			
	}
}
