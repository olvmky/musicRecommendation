// Jingtao Han

package blog.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blog.model.*;

public class CreditCardsDao {
    protected ConnectionManager connectionManager;
    private static CreditCardsDao instance = null;

    protected CreditCardsDao() {
        connectionManager = new ConnectionManager();
    }

    public static CreditCardsDao getInstance() {
        if (instance == null) {
            instance = new CreditCardsDao();
        }
        return instance;
    }

    // Create a new CreditCard
    public CreditCards create(CreditCards creditCard) throws SQLException {
        String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCreditCard);
            insertStmt.setLong(1, creditCard.getCardNumber());
            insertStmt.setDate(2, new java.sql.Date(creditCard.getExpiration().getTime()));
            insertStmt.setString(3, creditCard.getUser().getUserName());
            insertStmt.executeUpdate();
            return creditCard;
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

    // Get CreditCard by card number
    public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
        String selectCreditCard = "SELECT CardNumber, Expiration, UserName FROM CreditCards WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditCard);
            selectStmt.setLong(1, cardNumber);
            results = selectStmt.executeQuery();
            if (results.next()) {
                long resultCardNumber = results.getLong("CardNumber");
                Date expiration = new Date(results.getDate("Expiration").getTime());
                String userName = results.getString("UserName");
                Users user = UsersDao.getInstance().getUserByUserName(userName); // Assuming UsersDao exists
                CreditCards creditCard = new CreditCards(resultCardNumber, expiration, user);
                return creditCard;
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

    // Get CreditCards by user name
    public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
        List<CreditCards> creditCards = new ArrayList<>();
        String selectCreditCards = "SELECT CardNumber, Expiration, UserName FROM CreditCards WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditCards);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            while (results.next()) {
                long cardNumber = results.getLong("CardNumber");
                Date expiration = new Date(results.getDate("Expiration").getTime());
                String resultUserName = results.getString("UserName");
                Users user = UsersDao.getInstance().getUserByUserName(resultUserName);
                CreditCards creditCard = new CreditCards(cardNumber, expiration, user);
                creditCards.add(creditCard);
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
        return creditCards;
    }

    // Update expiration date of a CreditCard
    public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
        String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCreditCard);
            updateStmt.setDate(1, new java.sql.Date(newExpiration.getTime()));
            updateStmt.setLong(2, creditCard.getCardNumber());
            updateStmt.executeUpdate();
            creditCard.setExpiration(newExpiration);
            return creditCard;
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

    // Delete a CreditCard
    public CreditCards delete(CreditCards creditCard) throws SQLException {
        String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCreditCard);
            deleteStmt.setLong(1, creditCard.getCardNumber());
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