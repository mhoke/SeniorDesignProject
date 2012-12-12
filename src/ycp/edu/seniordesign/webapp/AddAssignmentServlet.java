package ycp.edu.seniordesign.webapp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ycp.edu.seniordesign.controller.AssignmentController;
import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;
import ycp.edu.seniordesign.util.CreateAssignmentsFromExcelFile;

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
			AssignmentController controller = new AssignmentController();
			if(req.getQueryString() != null && req.getQueryString().contains("id="))
			{
				courseID = Integer.parseInt(req.getQueryString().split("=")[1]);
			}
			
			if(courseID != -1)
			{
				try 
				{
					req.setAttribute("ListofGrades", controller.getWeights(courseID));
					req.setAttribute("courseID", courseID);
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
			int courseID = -1;
			AssignmentController controller = new AssignmentController();
			if(req.getQueryString() != null && req.getQueryString().contains("id="))
			{
				courseID = Integer.parseInt(req.getQueryString().split("=")[1]);
			}
			if(courseID != -1)
			{
				try 
				{
					req.setAttribute("ListofGrades", controller.getWeights(courseID));
					req.setAttribute("courseID", courseID);
					req.getRequestDispatcher("/view/addAssignment.jsp").forward(req, resp);
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			int userID = ((User) req.getSession().getAttribute("user")).getId();
			req.setAttribute("courseID", courseID);
			
			//Get text fields
			if(req.getParameter("AddAssignmentButton") != null)
			{
				String Name = req.getParameter("nameBox");
				int Year = Integer.parseInt(req.getParameter("yearBox"));
				int Month = Integer.parseInt(req.getParameter("monthBox"));
				int Day = Integer.parseInt(req.getParameter("dayBox"));
				int Possible = Integer.parseInt(req.getParameter("possibleBox"));
				int Weight = Integer.parseInt(req.getParameter("grade_weights"));
				
				Date date = new Date(Year - 1900, Month, Day);
				
				//Update Database
				try 
				{
					String Weight_Name = controller.getWeights(courseID).get(Weight).getName();
					int weight_id = controller.getWeightfromName(Weight_Name, courseID);
					controller.CreateAssignment(userID, courseID, Name, date, Possible, weight_id);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			else if(req.getParameter("UploadButton") != null)
			{
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// Parse the request
				try 
				{
					List<FileItem> items = upload.parseRequest(req);
					for(FileItem f : items)
					{
						if(!f.isFormField())
						{
							CreateAssignmentsFromExcelFile.createAssignmentsFromExcelSheet(f.getInputStream(), courseID);
						}
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			req.getRequestDispatcher("/view/addAssignment.jsp").forward(req, resp);
		}
	}
}
