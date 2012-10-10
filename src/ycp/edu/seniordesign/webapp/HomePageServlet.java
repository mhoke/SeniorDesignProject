package ycp.edu.seniordesign.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.HomePageController;
import ycp.edu.seniordesign.model.User;

public class HomePageServlet extends HttpServlet 
{
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("/view/homePage.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		User user = new User();
		
		HomePageController controller = new HomePageController();
		
		controller.setModel(user);
		
		String username = req.getParameter("usernameLabel");
		String errorMessage = null;
		
		
	}

}
