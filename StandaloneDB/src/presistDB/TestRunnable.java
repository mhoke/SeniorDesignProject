package presistDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mapcraftClasses.User;

public class TestRunnable {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws PersistenceException 
	 */
	public static void main(String[] args) throws SQLException, PersistenceException {
	
		/*
		User user = new User();
		String userName = "Megaman";
		String password = "x";
		//AddUser
		//DefaultDatabase.getInstance().addUser(userName, password);
		
		//CheckUser
		boolean checkUser = DefaultDatabase.getInstance().checkUser(userName);
		if(checkUser){
			System.out.println("CheckUser test sucess! User exist: "+ userName);
		}else{
			System.out.println("CheckUser could not find user: " + userName);
		}
		
		
		
		//CheckPassword
		boolean loginSucess = DefaultDatabase.getInstance().checkPassword(userName, password);
		if(loginSucess){
			System.out.println("Sucess! password correct");
		//GetUser
			user = DefaultDatabase.getInstance().getUser(userName);
			System.out.println("id: " + user.getId() + " name: " + user.getName() +password+ " password: " + user.getPassword()+ " description: "+ user.getDescription() +" rank: "+user.getRank() + " points: " + user.getPoints() + " radius: " + user.getRadius() + " geoLat: " + user.getGeoLat() + " geoLong: " + user.getGeoLong());
		}else{
			System.out.println("Fail! password incorrect");
		}
		
		
		//UpdateDescription
		System.out.println("Updating description...");
		DefaultDatabase.getInstance().updateDescription(user.getId(), "Capcom character made in my youngin days.");
		user = DefaultDatabase.getInstance().getUser(user.getName());
		System.out.println("id: " + user.getId() + " name: " + user.getName() +password+ " password: " + user.getPassword()+ " description: "+ user.getDescription() +" rank: "+user.getRank() + " points: " + user.getPoints() + " radius: " + user.getRadius() + " geoLat: " + user.getGeoLat() + " geoLong: " + user.getGeoLong());
	*/
	
	}

}
