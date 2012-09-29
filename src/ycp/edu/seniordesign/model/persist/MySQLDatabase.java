package ycp.edu.seniordesign.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import ycp.edu.seniordesign.model.User;

//import org.hsqldb.server.Server;

/**
 * Implementation of {@link IDatabase} using JDBC
 * and MySQL.
 */
public class MySQLDatabase implements IDatabase {
	// Details about connecting to the database.
	// Here we're assuming username=root, no password,
	// which is appropriate for XAMPP.
	public static String JDBC_URL =
			"jdbc:hsqldb:file:nenew.db";

	// Maximum number of times to attempt a transaction
	// before giving up.
	private static final int MAX_ATTEMPTS = 10;


	static {
		try {
			// Load the HSQL Database Engine JDBC driver
			//org.hsqldb.jdbc.JDBCDriver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not load SQLite JDBC driver", e);
		}
	}
	
	@Override
	public boolean checkUser(final String userName) throws PersistenceException {

		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "SELECT username FROM User WHERE username = ?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, userName);

				ResultSet resultSet = executeQuery(stmt);

				while (resultSet.next()) {
						isValid = true;			
				}
				stmt.close();
				return isValid;

			}

		});
	}

	@Override
	public boolean addUser(final User user) throws PersistenceException {

		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "INSERT INTO User values (NULL, ?, ?, ?, ?, ?)";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.setString(3, user.getSalt());
				stmt.setString(4, user.getEmailAddress());
				stmt.setInt(5, user.getType());
				stmt.executeUpdate();
				isValid = true;
				stmt.close();
				return isValid;
				
			}
			

		});
	}
	@Override
	public User getUser(final String userName) throws PersistenceException {

		return databaseRun(new AbstractDatabaseRunnable<User>() {

			@Override
			public User run(Connection conn) throws SQLException {
				User user = new User();
				String sqlstmt = "SELECT * FROM User WHERE username = ?";
				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, userName);
				ResultSet resultSet = executeQuery(stmt);

				if(resultSet.next()) {
					//TODO: package all user info into user class and return
					user.setId(Integer.parseInt(resultSet.getString("id")));
					user.setUsername((resultSet.getString("username")));
					user.setPassword((resultSet.getString("password")));
					user.setEmailAddress((resultSet.getString("emailAddress")));
					user.setSalt((resultSet.getString("salt")));
					user.setType(Integer.parseInt((resultSet.getString("userType"))));


				}
				//conn.commit();
				stmt.close();
				return user;

			}

		});
	}
/*
	@Override
	public boolean checkPassword(final String userName, final String password) throws PersistenceException {

		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "SELECT password FROM UserInfo WHERE name = ?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, userName);

				ResultSet resultSet = executeQuery(stmt);

				while (resultSet.next()) {
					if(resultSet.getString("password").equals(password)){
						isValid = true;
					}
				}
				stmt.close();
				return isValid;

			}

		});
	}
	
	@Override
	public Quest getQuest(final int questID) throws PersistenceException {
		return databaseRun(new AbstractDatabaseRunnable<Quest>() {
			@Override
			public Quest run(Connection conn) throws SQLException {
				Quest q = new Quest();
				String sqlstmt = "SELECT * FROM Quests WHERE id = ?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setInt(1, questID);

				ResultSet resultSet = executeQuery(stmt);
				while (resultSet.next()){
					q.setId(Integer.parseInt(resultSet.getString("id")));
					q.setName((resultSet.getString("name")));
					q.setGeoLat(Float.parseFloat((resultSet.getString("geolat"))));
					q.setGeoLong(Float.parseFloat((resultSet.getString("geolong"))));
					q.setPoints(Integer.parseInt((resultSet.getString("points"))));
					q.setDescription((resultSet.getString("description")));
					q.setComplete(Boolean.parseBoolean(resultSet.getString("complete")));
				}
				return q;
			}

		});
	}
	

	@Override
	public boolean addQuest(final String name, final String description, final int points, final float geoLat,final float geoLong) throws PersistenceException {

		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "INSERT INTO Quests values (NULL, ?, ?, ?, ?, ?, FALSE)";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, name);
				stmt.setInt(2, points);
				stmt.setFloat(3, geoLat);
				stmt.setFloat(4, geoLong);
				stmt.setString(5, description);
				stmt.executeUpdate();
				isValid = true;
				stmt.close();
				return isValid;
				
			}
			

		});
	}
	
	@Override
	public boolean updateDescription(final int userID,final String description)
			throws PersistenceException {
		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "UPDATE UserInfo SET description=? WHERE id=?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setString(1, description);
				stmt.setInt(2, userID);
				stmt.executeUpdate();
				isValid = true;
				stmt.close();
				return isValid;
				
			}
			

		});
	}
	@Override
	public boolean updateCompleteQuest(final int questID) throws PersistenceException {
		// TODO Auto-generated method stub
		return databaseRun(new AbstractDatabaseRunnable<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				boolean isValid = false;
				String sqlstmt = "UPDATE Quests SET complete=TRUE WHERE id=?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setInt(1, questID);
				stmt.executeUpdate();
				isValid = true;
				stmt.close();
				return isValid;
				
			}
			

		});
	}

	@Override
	public Location getLocation(final int locationID) throws PersistenceException {
		return databaseRun(new AbstractDatabaseRunnable<Location>() {
			@Override
			public Location run(Connection conn) throws SQLException {
				Location l = new Location();
				String sqlstmt = "SELECT * FROM Locations WHERE id = ?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setInt(1, locationID);

				ResultSet resultSet = executeQuery(stmt);
				while (resultSet.next()){
					//TODO: create location object, return it

				}
				
				stmt.close();
				return l;
			}

		});
	}
	@Override
	public String getRank(final int rankId) throws PersistenceException {
		return databaseRun(new AbstractDatabaseRunnable<String>() {
			@Override
			public String run(Connection conn) throws SQLException {
				String rank ="";
				String sqlstmt = "SELECT * FROM Rank WHERE id = ?";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setInt(1, rankId);

				ResultSet resultSet = executeQuery(stmt);
				while (resultSet.next()){
					//TODO: create location object, return it
					rank = resultSet.getString(1);
				}
				stmt.close();
				return rank;
			}

		});
	}
	@Override
	public int setRank(final int points) throws PersistenceException {
		return databaseRun(new AbstractDatabaseRunnable<Integer>() {
			@Override
			public Integer run(Connection conn) throws SQLException {
				int rankId = 0;
				String sqlstmt = "SELECT id FROM Rank WHERE minPoints > ?, maxPoints <= ?,";

				PreparedStatement stmt = prepareStatement(conn, sqlstmt);
				stmt.setInt(1, points);

				ResultSet resultSet = executeQuery(stmt);
				while (resultSet.next()){
					rankId = resultSet.getInt(1);
				}
				stmt.close();
				return rankId;

			}

		});
	}



	/**
	 * Run an {@link IDatabaseRunnable} in a transaction.
	 * 
	 * @param dbRunnable the IDatabaseRunnable
	 * @return the result of the IDatabaseRunnable
	 * @throws PersistenceException
	 */

	public<E> E databaseRun(IDatabaseRunnable<E> dbRunnable) throws PersistenceException {
		Connection conn = null;
		//Server hsqlServer = null;
		try {


			conn = DriverManager.getConnection(JDBC_URL);
			conn.setAutoCommit(false);
			int numAttempts = 0;
			E result = null;
			boolean committed = false;

			while (!committed && numAttempts < MAX_ATTEMPTS) {
				try {
					// Attempt the transaction.
					E tmpResult = dbRunnable.run(conn);
					conn.commit();
					
					// Success!
					result = tmpResult;
					committed = true;
				} catch (SQLException e) {
					e.printStackTrace();
					conn.rollback();
					// Check to see if the transaction aborted due to deadlock.
					// If so, we can retry it.
					// See: http://dev.mysql.com/doc/refman/5.0/en/connector-j-usagenotes-troubleshooting.html
					String sqlState = e.getSQLState();
					if (sqlState != null && sqlState.equals("40001")) {
						// Deadlock detected.
						numAttempts++;
					} else {
						// Some other failure: just rethrow the exception.
						//throw e;
					}
				}
			}

			if (numAttempts >= MAX_ATTEMPTS) {
				throw new PersistenceException("Transaction deadlock retry count exceeded");
			}
			conn.createStatement().executeUpdate("SHUTDOWN");
			return result;
		} catch (SQLException e) {
			throw new PersistenceException("Database error", e);
		} finally {
			dbRunnable.cleanup();
			DBUtil.closeQuietly(conn);
		}
	}
	

	/**
	 * Load the data for an Item from the current tuple in the given ResultSet.
	 * 
	 * @param item        the Item whose data should be loaded
	 * @param resultSet   the ResultSet
	 * @param index       the index in the ResultSet corresponding to the first field of the Item
	 * @throws SQLException
	 */
	//	protected void load(Item item, ResultSet resultSet, int index) throws SQLException {
	//		
	//		
	//		item.setId(resultSet.getInt(index++));
	//		item.setName(resultSet.getString(index++));
	//		item.setQuantity(resultSet.getInt(index++));
	//	}

	/**
	 * Store given Item's data in the given PreparedStatement,
	 * without storing the Item's id value.
	 * (It is assumed that a new id value will be generated for the item
	 * by the database as a generated key.)
	 * 
	 * @param item        the Item whose data should be stored
	 * @param insertStmt  the PreparedStatement
	 * @param index       the index in the PreparedStatement corresponding to the first
	 *                    field in the Item (excluding the id field)
	 * @throws SQLException
	 */
	//	protected void storeNoId(Item item, PreparedStatement insertStmt, int index) throws SQLException {
	//		insertStmt.setString(index++, item.getName());
	//		insertStmt.setInt(index++, item.getQuantity());
	//	}
}
