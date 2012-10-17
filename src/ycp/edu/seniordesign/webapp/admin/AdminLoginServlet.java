package ycp.edu.seniordesign.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ycp.edu.seniordesign.controller.admin.AdminLoginController;
import ycp.edu.seniordesign.model.Admin;

public class AdminLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("/view/admin/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = req.getParameter("usernameBox");
		String password = req.getParameter("passwordBox");
		
		AdminLoginController controller = new AdminLoginController();
		
		String errorMessage = null;		
		
		Admin admin = null;
						
		if(req.getParameter("loginButton") != null){
			try{
				admin = controller.login(username, password);
				
				if(admin == null){ 
					errorMessage = "Login failed.";
					req.getRequestDispatcher("/view//admin/login.jsp").forward(req, resp);
				}
				else{
					errorMessage = "Login successful";
					req.getSession().setAttribute("admin", admin);
				}
			}
			catch(Exception e){
				System.out.println("Login database fail");
				e.printStackTrace();
			}
		}
		System.out.println(errorMessage);
		req.setAttribute("errorMessage", errorMessage);
		
		if(admin != null){
			//Moves the page forward
			req.getRequestDispatcher("/view/admin/home.jsp").forward(req,resp);
		}			
	}
}
