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
		User user = null;
		
		LoginController controller = new LoginController();
		
		controller.setModel(user);
		
		String username = req.getParameter("usernameBox");
		String password = req.getParameter("passwordBox");
		
		String errorMessage = null;
				
		if(req.getParameter("loginButton") != null)
		{
			try
			{
				User result = controller.login(username, password);
				
				if(result == null)
				{ 
					errorMessage = "Login failed";
				}
				else
				{
					errorMessage = "Login successful";
					req.getSession().setAttribute("user", result);
					System.out.println(result.getUsername());
				}
			}
			catch(Exception e)
			{
				System.out.println("Login database fail");
			}
		}
		
		else if(req.getParameter("registerButton") != null)
		{
			System.out.println("Registering a new account");
			req.getRequestDispatcher("/view/createAccount.jsp").forward(req, resp);
		}
		
		req.setAttribute("errorMessage", errorMessage);
		
		if(user != null)
		{
			//Moves the page forward
			//req.getRequestDispatcher("/view/homePage.jsp").forward(req,resp);
		}			
	}
}
