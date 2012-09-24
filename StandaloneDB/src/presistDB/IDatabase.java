package presistDB;

import java.util.List;

import mapcraftClasses.Location;
import mapcraftClasses.Quest;
import mapcraftClasses.User;

/**
 * Interface describing the persistence operations
 * (for loading/storing model objects.)
 */
public interface IDatabase {
	/**
	 * Look up a single Item by name.
	 * 
	 * @param itemName  name of the Item to find
	 * @return the Item with the given name, or null if there is no such item
	 * @throws PersistenceException
	 */
	//user info database retrievals
	/*
	boolean checkPassword(String userName, String password) throws PersistenceException;

	boolean addUser(String userName, String password) throws PersistenceException;
	
	boolean checkUser(String userName) throws PersistenceException;

	User getUser(String userName) throws PersistenceException;

	int setRank(int points) throws PersistenceException;

	String getRank(int rankId) throws PersistenceException;
	
	boolean updateDescription(int userID, String description) throws PersistenceException;
	
	//location database retrievals
	
	Location getLocation(int locationID) throws PersistenceException;
	
	
	//quest database retrievals

	Quest getQuest(int questID) throws PersistenceException;
	
	boolean updateCompleteQuest(int questID) throws PersistenceException;
	
	boolean addQuest(String name, String description, int points, float geoLat, float geoLong) throws PersistenceException;
*/
	/**
	 * Get all Items in the inventory.
	 * 
	 * @return List of all Items in the inventory
	 */
}
//	public List<Item> getAllItems() throws PersistenceException;
//
//	
//	/**
//	 * Replace an Item's data.
//	 * 
//	 * @param theItem the Item object containing the updated data
//	 * @return true if the Item's data is successfully replaced,
//	 *         or false if there is no such item
//	 */
//	public Boolean replaceItemData(Item theItem) throws PersistenceException;
//
//	/**
//	 * Replace all Items in the inventory with the given ones.
//	 * If successful, the Item objects will be updated with their
//	 * new unique ids in the database.
//	 * 
//	 * @param allItems the new Items to store in the database
//	 */
//	public void replaceAllItems(List<Item> allItems) throws PersistenceException;
//
//	/**
//	 * Add a new Item to the inventory.
//	 * It must have a different name than any existing Item.
//	 * If the item is added successfully, the Item object will have
//	 * its unique id set.
//	 * 
//	 * @param item  the Item to add
//	 * @return true if the Item is added successfully, false if there is
//	 *         already an Item with the same name
//	 */
//	public boolean addNewItem(Item item) throws PersistenceException;
//	
//	/**
//	 * Delete the named Item.
//	 * 
//	 * @param itemName the name of the Item to delete
//	 * @return true if item was deleted successfully, false if no such item exists
//	 * @throws PersistenceException
//	 */
//	public boolean deleteItem(String itemName) throws PersistenceException;
//	
//	/**
//	 * Delete all Items from the inventory.
//	 */
//	public void deleteAllItems() throws PersistenceException;
//}
