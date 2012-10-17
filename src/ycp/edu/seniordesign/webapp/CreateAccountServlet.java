package ycp.edu.seniordesign.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.CreateAccountController;

public class CreateAccountServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("/view/createAccount.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		CreateAccountController controller = new CreateAccountController();
		
		String username = req.getParameter("usernameBox");
		String password = req.getParameter("passwordBox");
		String email = req.getParameter("emailBox");
		
		String errorMessage = null;
		
		boolean result = false;

		if(req.getParameter("createButton") != null)
		{
			System.out.println("Enters here");
			try
			{
				result = controller.createAccount(username, password, email);
				
				if(!result)
				{
					errorMessage = "Create Account failed";
				}
				else
				{
					errorMessage = "Create Account successful";
				}
			}
			catch(Exception e)
			{
				System.out.println("Create Account fail");
			}
		}
		
		req.setAttribute("errorMessage", errorMessage);
		
		req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
	}
}

