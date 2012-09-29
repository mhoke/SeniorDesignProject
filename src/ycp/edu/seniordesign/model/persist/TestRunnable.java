package ycp.edu.seniordesign.model.persist;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.User;

public class TestRunnable {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws PersistenceException 
	 */
	public static void main(String[] args) throws SQLException, PersistenceException {


		User user = new User();
		user.setUsername("jay");
		user.setPassword("ham");
		user.setEmailAddress("theBaths@ycp.edu");
		user.setSalt("peppery");
		
		//checkUser()
		boolean checkUser = DefaultDatabase.getInstance().checkUser(user.getUsername());
		if(checkUser){
			System.out.println("CheckUser test sucess! User exist: "+ user.getUsername());
			//getUser()
			System.out.println("Getting the user...");
			user = DefaultDatabase.getInstance().getUser(user.getUsername());
			System.out.println("User_id: "+ user.getId());
		}else{
			System.out.println("CheckUser could not find user: " + user.getUsername());
			
			//AddUser
			System.out.println("Adding user...");
			DefaultDatabase.getInstance().addUser(user);
			System.out.println("Getting user... " + user.getUsername());
			user = DefaultDatabase.getInstance().getUser(user.getUsername());
			System.out.println("User_id: "+ user.getId());

			
		}



		//CheckPassword
		

		//		
		//		
		//		//UpdateDescription
		//		System.out.println("Updating description...");
		//		DefaultDatabase.getInstance().updateDescription(user.getId(), "Capcom character made in my youngin days.");
		//		user = DefaultDatabase.getInstance().getUser(user.getName());
		//		System.out.println("id: " + user.getId() + " name: " + user.getName() +password+ " password: " + user.getPassword()+ " description: "+ user.getDescription() +" rank: "+user.getRank() + " points: " + user.getPoints() + " radius: " + user.getRadius() + " geoLat: " + user.getGeoLat() + " geoLong: " + user.getGeoLong());
		//	

	}

}
