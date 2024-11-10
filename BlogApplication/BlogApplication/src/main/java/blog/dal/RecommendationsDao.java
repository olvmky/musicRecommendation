// Jingtao Han
package blog.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import blog.model.*;

public class RecommendationsDao {
    protected ConnectionManager connectionManager;

    private static RecommendationsDao instance = null;
    protected RecommendationsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecommendationsDao getInstance() {
        if(instance == null) {
            instance = new RecommendationsDao();
        }
        return instance;
    }

    /**
     * Create a new recommendation
     */
    public Recommendations create(Recommendations recommendation) throws SQLException {
        String insertRecommendation = 
            "INSERT INTO Recommendations(UserName, RestaurantId) " +
            "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation,
                Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recommendation.getUser().getUserName());
            insertStmt.setInt(2, recommendation.getRestaurant().getRestaurantId());

            insertStmt.executeUpdate();
            resultKey = insertStmt.getGeneratedKeys();
            int recommendationId = -1;
            if(resultKey.next()) {
                recommendationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendation.setRecommendationId(recommendationId);
            return recommendation;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }

    /**
     * Get a recommendation by its ID
     */
    public Recommendations getRecommendationById(int recommendationId) throws SQLException {
        String selectRecommendation = 
            "SELECT RecommendationId, UserName, RestaurantId " +
            "FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();

            if(results.next()) {
                int resultRecommendationId = results.getInt("RecommendationId");
                String userName = results.getString("UserName");
                int restaurantId = results.getInt("RestaurantId");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);

                Recommendations recommendation = new Recommendations(resultRecommendationId, user, restaurant);
                return recommendation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }

    /**
     * Get all recommendations made by a specific user
     */
    public List<Recommendations> getRecommendationsByUserName(String userName) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<>();
        String selectRecommendations = 
            "SELECT RecommendationId, UserName, RestaurantId " +
            "FROM Recommendations WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendations);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();

            while(results.next()) {
                int recommendationId = results.getInt("RecommendationId");
                int restaurantId = results.getInt("RestaurantId");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Recommendations recommendation = new Recommendations(recommendationId, user, restaurant);
                recommendations.add(recommendation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return recommendations;
    }

    /**
     * Get all recommendations for a specific restaurant
     */
    public List<Recommendations> getRecommendationsByRestaurantId(int restaurantId) throws SQLException {
        List<Recommendations> recommendations = new ArrayList<>();
        String selectRecommendations = 
            "SELECT RecommendationId, UserName, RestaurantId " +
            "FROM Recommendations WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendations);
            selectStmt.setInt(1, restaurantId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();

            while(results.next()) {
                int recommendationId = results.getInt("RecommendationId");
                String userName = results.getString("UserName");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Recommendations recommendation = new Recommendations(recommendationId, user, restaurant);
                recommendations.add(recommendation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return recommendations;
    }

    /**
     * Delete a recommendation by recommendationId
     */
    public Recommendations delete(Recommendations recommendation) throws SQLException {
        String deleteRecommendation = 
            "DELETE FROM Recommendations WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendation.getRecommendationId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}