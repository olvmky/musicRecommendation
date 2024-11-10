// Jingtao Han
package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.model.*;

/**
 * Data access object (DAO) class to interact with the underlying TakeOutRestaurants table in your
 * MySQL instance. This is used to store {@link TakeOutRestaurants} into your MySQL instance and 
 * retrieve {@link TakeOutRestaurants} from MySQL instance.
 */
public class TakeOutRestaurantsDao extends RestaurantsDao {
	// Single pattern: instantiation is limited to one object.
	private static TakeOutRestaurantsDao instance = null;
	protected TakeOutRestaurantsDao() {
		super();
	}
	public static TakeOutRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}

	/**
	 * Create a new TakeOutRestaurant in the database.
	 */
	public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(takeOutRestaurant.getName(), 
				takeOutRestaurant.getDescription(), takeOutRestaurant.getMenu(),
				takeOutRestaurant.getHours(), takeOutRestaurant.isActive(),
				takeOutRestaurant.getCuisineType(), takeOutRestaurant.getStreet1(),
				takeOutRestaurant.getStreet2(), takeOutRestaurant.getCity(),
				takeOutRestaurant.getState(), takeOutRestaurant.getZip(),
				takeOutRestaurant.getCompany()));

		String insertTakeOutRestaurant = 
				"INSERT INTO TakeOutRestaurant(RestaurantId, MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			insertStmt.executeUpdate();
			takeOutRestaurant.setRestaurantId(restaurant.getRestaurantId());
			return takeOutRestaurant;
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
	 * Get a TakeOutRestaurant by its ID.
	 */
	public TakeOutRestaurants getTakeOutRestaurantById(int takeOutRestaurantId) throws SQLException {
		String selectTakeOutRestaurant =
				"SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, MaxWaitTime " +
				"FROM TakeOutRestaurant INNER JOIN Restaurants " +
				"  ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE TakeOutRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setInt(1, takeOutRestaurantId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				Companies company = new Companies(results.getString("CompanyName"));
				int maxWaitTime = results.getInt("MaxWaitTime");

				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(resultRestaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, maxWaitTime);
				return takeOutRestaurant;
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
	 * Get a list of TakeOutRestaurants by the company name.
	 */
	public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException {
		List<TakeOutRestaurants> takeOutRestaurants = new ArrayList<TakeOutRestaurants>();
		String selectTakeOutRestaurants =
				"SELECT TakeOutRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, MaxWaitTime " +
				"FROM TakeOutRestaurant INNER JOIN Restaurants " +
				"  ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				boolean active = results.getBoolean("Active");
				Restaurants.CuisineType cuisineType = Restaurants.CuisineType.valueOf(results.getString("CuisineType"));
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				Companies company = new Companies(results.getString("CompanyName"));
				int maxWaitTime = results.getInt("MaxWaitTime");

				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(restaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, maxWaitTime);
				takeOutRestaurants.add(takeOutRestaurant);
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
		return takeOutRestaurants;
	}

	/**
	 * Delete the TakeOutRestaurant from the database.
	 */
	public TakeOutRestaurants delete(TakeOutRestaurants takeOutRestaurant) throws SQLException {
		String deleteTakeOutRestaurant = "DELETE FROM TakeOutRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOutRestaurant);
			deleteStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Also delete from the Restaurants superclass.
			super.delete(takeOutRestaurant);

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