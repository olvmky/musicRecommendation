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
 * Data access object (DAO) class to interact with the underlying SitDownRestaurants table in your
 * MySQL instance. This is used to store {@link SitDownRestaurants} into your MySQL instance and 
 * retrieve {@link SitDownRestaurants} from MySQL instance.
 */
public class SitDownRestaurantsDao extends RestaurantsDao {
	// Single pattern: instantiation is limited to one object.
	private static SitDownRestaurantsDao instance = null;
	protected SitDownRestaurantsDao() {
		super();
	}
	public static SitDownRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantsDao();
		}
		return instance;
	}

	/**
	 * Create a new SitDownRestaurant in the database.
	 */
	public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(sitDownRestaurant.getName(), 
				sitDownRestaurant.getDescription(), sitDownRestaurant.getMenu(),
				sitDownRestaurant.getHours(), sitDownRestaurant.isActive(),
				sitDownRestaurant.getCuisineType(), sitDownRestaurant.getStreet1(),
				sitDownRestaurant.getStreet2(), sitDownRestaurant.getCity(),
				sitDownRestaurant.getState(), sitDownRestaurant.getZip(),
				sitDownRestaurant.getCompany()));

		String insertSitDownRestaurant = 
				"INSERT INTO SitDownRestaurant(RestaurantId, Capacity) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSitDownRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
			insertStmt.executeUpdate();
			sitDownRestaurant.setRestaurantId(restaurant.getRestaurantId());
			return sitDownRestaurant;
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
	 * Get a SitDownRestaurant by its ID.
	 */
	public SitDownRestaurants getSitDownRestaurantById(int sitDownRestaurantId) throws SQLException {
		String selectSitDownRestaurant =
				"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Capacity " +
				"FROM SitDownRestaurant INNER JOIN Restaurants " +
				"  ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE SitDownRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setInt(1, sitDownRestaurantId);
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
				int capacity = results.getInt("Capacity");

				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(resultRestaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, capacity);
				return sitDownRestaurant;
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
	 * Get a list of SitDownRestaurants by the company name.
	 */
	public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {
		List<SitDownRestaurants> sitDownRestaurants = new ArrayList<SitDownRestaurants>();
		String selectSitDownRestaurants =
				"SELECT SitDownRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Capacity " +
				"FROM SitDownRestaurant INNER JOIN Restaurants " +
				"  ON SitDownRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurants);
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
				int capacity = results.getInt("Capacity");

				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(restaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, capacity);
				sitDownRestaurants.add(sitDownRestaurant);
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
		return sitDownRestaurants;
	}

	/**
	 * Delete the SitDownRestaurant from the database.
	 */
	public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurant) throws SQLException {
		String deleteSitDownRestaurant = "DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurant);
			deleteStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Also delete from the Restaurants superclass.
			super.delete(sitDownRestaurant);

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