package musicapplication.dal;

import musicapplication.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class to interact with the ListeningHistory table in
 * the database.
 */
public class ListeningHistoryDao {
    protected ConnectionManager connectionManager;

    private static ListeningHistoryDao instance = null;

    // Private constructor to prevent instantiation
    protected ListeningHistoryDao() {
        connectionManager = new ConnectionManager();
    }

    // Singleton method to get the instance
    public static ListeningHistoryDao getInstance() {
        if (instance == null) {
            instance = new ListeningHistoryDao();
        }
        return instance;
    }

    // SQL statements
    private static final String INSERT_LISTENING_HISTORY = "INSERT INTO ListeningHistory(TrackId, UserName, ListeningTime) VALUES(?,?,?);";
    private static final String SELECT_LISTENING_HISTORY = "SELECT ListeningHistoryId, TrackId, UserName, ListeningTime FROM ListeningHistory WHERE ListeningHistoryId=?;";
    private static final String SELECT_LISTENING_HISTORY_BY_USER = "SELECT ListeningHistoryId, TrackId, UserName, ListeningTime FROM ListeningHistory WHERE UserName=?;";
    private static final String SELECT_LISTENING_HISTORY_BY_TRACK = "SELECT ListeningHistoryId, TrackId, UserName, ListeningTime FROM ListeningHistory WHERE TrackId=?;";
    private static final String DELETE_LISTENING_HISTORY = "DELETE FROM ListeningHistory WHERE ListeningHistoryId=?;";
    
    // Create a new ListeningHistory record
    public ListeningHistory create(ListeningHistory listeningHistory) throws SQLException {
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(INSERT_LISTENING_HISTORY, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, listeningHistory.getTrackId());
            insertStmt.setString(2, listeningHistory.getUserName());
            insertStmt.setTimestamp(3, new Timestamp(listeningHistory.getListeningTime().getTime()));
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int listeningHistoryId = -1;
            if (resultKey.next()) {
                listeningHistoryId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            listeningHistory.setListeningHistoryId(listeningHistoryId);
            return listeningHistory;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (insertStmt != null) insertStmt.close();
            if (resultKey != null) resultKey.close();
        }
    }

    // Retrieve a ListeningHistory by its ID
    public ListeningHistory getListeningHistoryById(int listeningHistoryId) throws SQLException {
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(SELECT_LISTENING_HISTORY);
            selectStmt.setInt(1, listeningHistoryId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultId = results.getInt("ListeningHistoryId");
                String trackId = results.getString("TrackId");
                String userName = results.getString("UserName");
                Timestamp listeningTime = results.getTimestamp("ListeningTime");
                return new ListeningHistory(resultId, trackId, userName, new Date(listeningTime.getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (selectStmt != null) selectStmt.close();
            if (results != null) results.close();
        }
        return null;
    }

    // Retrieve ListeningHistory records by user
    public List<ListeningHistory> getListeningHistoryByUser(String userName) throws SQLException {
        List<ListeningHistory> historyList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(SELECT_LISTENING_HISTORY_BY_USER);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();

            while (results.next()) {
                int resultId = results.getInt("ListeningHistoryId");
                String trackId = results.getString("TrackId");
                Timestamp listeningTime = results.getTimestamp("ListeningTime");
                ListeningHistory history = new ListeningHistory(resultId, trackId, userName, new Date(listeningTime.getTime()));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (selectStmt != null) selectStmt.close();
            if (results != null) results.close();
        }
        return historyList;
    }

    // Delete a ListeningHistory record
    public ListeningHistory delete(ListeningHistory listeningHistory) throws SQLException {
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(DELETE_LISTENING_HISTORY);
            deleteStmt.setInt(1, listeningHistory.getListeningHistoryId());
            deleteStmt.executeUpdate();
            return null; // Return null after deletion
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (deleteStmt != null) deleteStmt.close();
        }
    }
}
