package musicapplication.dal;

import musicapplication.model.*;
import java.sql.*;

/**
 * Data Access Object (DAO) class to interact with the Users table in the
 * database.
 * 
 * @author Krushna Sanjay Sharma
 */
public class UsersDao {
	private static volatile UsersDao instance;
	private ConnectionManager connectionManager;

	// SQL statement constants
	private static final String INSERT_USER = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?);";
	private static final String SELECT_USER = "SELECT UserName,Password,FirstName,LastName,Email,Phone FROM Users WHERE UserName=?;";
	private static final String UPDATE_USER = "UPDATE Users SET Password=?,FirstName=?,LastName=?,Email=?,Phone=? WHERE UserName=?;";
	private static final String DELETE_USER = "DELETE FROM Users WHERE UserName=?;";

	// Column name constants
	private static final String COLUMN_USERNAME = "UserName";
	private static final String COLUMN_PASSWORD = "Password";
	private static final String COLUMN_FIRSTNAME = "FirstName";
	private static final String COLUMN_LASTNAME = "LastName";
	private static final String COLUMN_EMAIL = "Email";
	private static final String COLUMN_PHONE = "Phone";

	/**
	 * Private constructor for Singleton UsersDao.
	 */
	private UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	/**
	 * Get singleton instance for UsersDao 
	 * @return the singleton instance
	 */
    public static UsersDao getInstance() {
        if (instance == null) {
            synchronized (UsersDao.class) {
                if (instance == null) {
                    instance = new UsersDao();
                }
            }
        }
        return instance;
    }

	/**
	 * Create a new user in the database.
	 * 
	 * @param user The user to be created.
	 * @return The created user.
	 * @throws SQLException if a database access error occurs.
	 */
	public Users create(Users user) throws SQLException {
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(INSERT_USER);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setString(6, user.getPhone());
			insertStmt.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Retrieve a user by their username.
	 * 
	 * @param userName The username of the user to retrieve.
	 * @return The retrieved user, or null if not found.
	 * @throws SQLException if a database access error occurs.
	 */
	public Users getUserByUserName(String userName) throws SQLException {
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(SELECT_USER);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUserName = results.getString(COLUMN_USERNAME);
				String password = results.getString(COLUMN_PASSWORD);
				String firstName = results.getString(COLUMN_FIRSTNAME);
				String lastName = results.getString(COLUMN_LASTNAME);
				String email = results.getString(COLUMN_EMAIL);
				String phone = results.getString(COLUMN_PHONE);
				return new Users(resultUserName, password, firstName, lastName, email, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Update an existing user in the database.
	 * 
	 * @param user The user to be updated.
	 * @return The updated user.
	 * @throws SQLException if a database access error occurs.
	 */
	public Users updateUser(Users user) throws SQLException {
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(UPDATE_USER);
			updateStmt.setString(1, user.getPassword());
			updateStmt.setString(2, user.getFirstName());
			updateStmt.setString(3, user.getLastName());
			updateStmt.setString(4, user.getEmail());
			updateStmt.setString(5, user.getPhone());
			updateStmt.setString(6, user.getUserName());
			updateStmt.executeUpdate();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete a user from the database.
	 * 
	 * @param user The user to be deleted.
	 * @return null if the deletion was successful.
	 * @throws SQLException if a database access error occurs.
	 */
	public Users delete(Users user) throws SQLException {
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(DELETE_USER);
			deleteStmt.setString(1, user.getUserName());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
