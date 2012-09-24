package ycp.edu.seniordesign.model.persist;

/**
 * Class used to access the default instance of
 * {@link IDatabase}.
 */
public abstract class DefaultDatabase {
	private static final IDatabase instance =
			new MySQLDatabase();
	
	public static IDatabase getInstance() {
		return instance;
	}
}
