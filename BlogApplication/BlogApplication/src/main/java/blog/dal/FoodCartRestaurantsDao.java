// Jingtao Han

package blog.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blog.model.*;

/**
 * Data access object (DAO) class to interact with the underlying FoodCartRestaurants table in your
 * MySQL instance. This is used to store {@link FoodCartRestaurants} into your MySQL instance and 
 * retrieve {@link FoodCartRestaurants} from the MySQL instance.
 */
public class FoodCartRestaurantsDao extends RestaurantsDao {
	// Single pattern: instantiation is limited to one object.
	private static FoodCartRestaurantsDao instance = null;
	protected FoodCartRestaurantsDao() {
		super();
	}
	public static FoodCartRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantsDao();
		}
		return instance;
	}

	/**
	 * Create a new FoodCartRestaurant in the database.
	 */
	public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		// Insert into the superclass table first.
		Restaurants restaurant = create(new Restaurants(foodCartRestaurant.getName(),
				foodCartRestaurant.getDescription(), foodCartRestaurant.getMenu(),
				foodCartRestaurant.getHours(), foodCartRestaurant.isActive(),
				foodCartRestaurant.getCuisineType(), foodCartRestaurant.getStreet1(),
				foodCartRestaurant.getStreet2(), foodCartRestaurant.getCity(),
				foodCartRestaurant.getState(), foodCartRestaurant.getZip(),
				foodCartRestaurant.getCompany()));

		String insertFoodCartRestaurant =
				"INSERT INTO FoodCartRestaurant(RestaurantId, Licensed) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodCartRestaurant);
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
			insertStmt.executeUpdate();
			foodCartRestaurant.setRestaurantId(restaurant.getRestaurantId());
			return foodCartRestaurant;
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
	 * Get a FoodCartRestaurant by its ID.
	 */
	public FoodCartRestaurants getFoodCartRestaurantById(int foodCartRestaurantId) throws SQLException {
		String selectFoodCartRestaurant =
				"SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Licensed " +
				"FROM FoodCartRestaurant INNER JOIN Restaurants " +
				"  ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE FoodCartRestaurant.RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
			selectStmt.setInt(1, foodCartRestaurantId);
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
				boolean licensed = results.getBoolean("Licensed");

				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(resultRestaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, licensed);
				return foodCartRestaurant;
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
	 * Get a list of FoodCartRestaurants by the company name.
	 */
	public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException {
		List<FoodCartRestaurants> foodCartRestaurants = new ArrayList<FoodCartRestaurants>();
		String selectFoodCartRestaurants =
				"SELECT FoodCartRestaurant.RestaurantId AS RestaurantId, Name, Description, Menu, Hours, " +
				"Active, CuisineType, Street1, Street2, City, State, Zip, CompanyName, Licensed " +
				"FROM FoodCartRestaurant INNER JOIN Restaurants " +
				"  ON FoodCartRestaurant.RestaurantId = Restaurants.RestaurantId " +
				"WHERE Restaurants.CompanyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurants);
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
				boolean licensed = results.getBoolean("Licensed");

				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(restaurantId, name, description, 
					menu, hours, active, cuisineType, street1, street2, city, state, zip, company, licensed);
				foodCartRestaurants.add(foodCartRestaurant);
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
		return foodCartRestaurants;
	}

	/**
	 * Delete the FoodCartRestaurant from the database.
	 */
	public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		String deleteFoodCartRestaurant = "DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodCartRestaurant);
			deleteStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();

			// Also delete from the Restaurants superclass.
			super.delete(foodCartRestaurant);

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