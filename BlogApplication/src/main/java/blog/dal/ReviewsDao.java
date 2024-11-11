// Jingtao Han
package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blog.model.*;

public class ReviewsDao {
    protected BlogConnectionManager connectionManager;

    private static ReviewsDao instance = null;
    protected ReviewsDao() {
        connectionManager = new BlogConnectionManager();
    }
    public static ReviewsDao getInstance() {
        if(instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }

    // Create a new Review
    public Reviews create(Reviews review) throws SQLException {
        String insertReview = 
            "INSERT INTO Reviews(Created,Content,Rating,UserName,RestaurantId) " +
            "VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, new Timestamp(review.getCreated().getTime()));
            insertStmt.setString(2, review.getContent());
            insertStmt.setDouble(3, review.getRating());
            insertStmt.setString(4, review.getUser().getUserName());
            insertStmt.setInt(5, review.getRestaurant().getRestaurantId());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if(resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);
            return review;
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

    // Get a review by its ID
    public Reviews getReviewById(int reviewId) throws SQLException {
        String selectReview = 
            "SELECT ReviewId,Created,Content,Rating,UserName,RestaurantId " +
            "FROM Reviews " +
            "WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            if(results.next()) {
                int resultReviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                String userName = results.getString("UserName");
                int restaurantId = results.getInt("RestaurantId");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Reviews review = new Reviews(resultReviewId, created, content, rating, user, restaurant);
                return review;
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

    // Get all reviews for a specific user
    public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
        List<Reviews> reviewsList = new ArrayList<>();
        String selectReviews = 
            "SELECT ReviewId,Created,Content,Rating,UserName,RestaurantId " +
            "FROM Reviews " +
            "WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            while(results.next()) {
                int reviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                int restaurantId = results.getInt("RestaurantId");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Reviews review = new Reviews(reviewId, created, content, rating, user, restaurant);
                reviewsList.add(review);
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
        return reviewsList;
    }

    // Get all reviews for a specific restaurant
    public List<Reviews> getReviewsByRestaurantId(int restaurantId) throws SQLException {
        List<Reviews> reviewsList = new ArrayList<>();
        String selectReviews = 
            "SELECT ReviewId,Created,Content,Rating,UserName,RestaurantId " +
            "FROM Reviews " +
            "WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setInt(1, restaurantId);
            results = selectStmt.executeQuery();
            UsersDao usersDao = UsersDao.getInstance();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            while(results.next()) {
                int reviewId = results.getInt("ReviewId");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                String userName = results.getString("UserName");

                Users user = usersDao.getUserByUserName(userName);
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Reviews review = new Reviews(reviewId, created, content, rating, user, restaurant);
                reviewsList.add(review);
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
        return reviewsList;
    }

    // Delete a review
    public Reviews delete(Reviews review) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Reviews instance.
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