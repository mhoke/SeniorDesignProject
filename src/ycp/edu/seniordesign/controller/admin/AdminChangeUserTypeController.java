package ycp.edu.seniordesign.controller.admin;

import ycp.edu.seniordesign.model.User;
import ycp.edu.seniordesign.model.persist.Database;

public class AdminChangeUserTypeController {

	public boolean changeUserType(String name, String emailAddress, String userType) throws Exception {
		// Convert the string usertype value into the corresponding int
		int userTypeValue;
		if (userType.equals("Student")){
			userTypeValue = User.STUDENT_PROFILE;
		} else if (userType.equals("Professor")){
			userTypeValue = User.PROFESSOR_PROFILE;
		} else if (userType.equals("Both")){
			userTypeValue = User.PROFESSOR_STUDENT_PROFILE;
		} else {
			throw new Exception("Invalid user type (this should not happen");
		}
		
		// get the id of the user account that needs changed
		User user = Database.getInstance().getUserByEmail(emailAddress);
		if (user == null){
			// No user found with the given email address
			return false;
		} else if (!user.getName().equals(name)){		
			return false;
		}
		
		// change the id and update it in the database
		user.setType(userTypeValue);
		Database.getInstance().changeUserType(user);
		
		return true;
	}

}
