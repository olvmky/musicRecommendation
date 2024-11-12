package musicapplication.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import musicapplication.model.Playlists;

/**
 * Data Access Object (DAO) class to interact with the Users table in the
 * database.
 * 
 * @author Jia Ying Li
 */
public class PlaylistsDao {
	protected ConnectionManager connectionManager;
	private static PlaylistsDao instance = null;
	protected PlaylistsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlaylistsDao getInstance() {
		if(instance == null) {
			instance = new PlaylistsDao();
		}
		return instance;
	}
	
	/**
     * Create a new Playlist in the database.
     */
    public Playlists create(Playlists playlist) throws SQLException {
        String insertPlaylist = "INSERT INTO Playlists(Created, IsPublic, UserName, Name) VALUES(?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPlaylist, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, playlist.getCreated());
            insertStmt.setBoolean(2, playlist.isIsPublic());
            insertStmt.setString(3, playlist.getUserName());
            insertStmt.setString(4, playlist.getName());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int playlistId = -1;
            if (resultKey.next()) {
                playlistId = resultKey.getInt(1);
            }
            playlist.setPlaylistId(playlistId);
            return playlist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultKey != null) resultKey.close();
            if (insertStmt != null) insertStmt.close();
            if (connection != null) connection.close();
        }
    }

    /**
     * Get a Playlist by its ID.
     */
    public Playlists getPlaylistById(int playlistId) throws SQLException {
        String selectPlaylist = "SELECT PlaylistId, Created, IsPublic, UserName, Name FROM Playlists WHERE PlaylistId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPlaylist);
            selectStmt.setInt(1, playlistId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                Timestamp created = results.getTimestamp("Created");
                boolean isPublic = results.getBoolean("IsPublic");
                String userName = results.getString("UserName");
                String name = results.getString("Name");

                return new Playlists(playlistId, created, isPublic, userName, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (results != null) results.close();
            if (selectStmt != null) selectStmt.close();
            if (connection != null) connection.close();
        }
        return null;
    }

    /**
     * Update the Name and visibility (IsPublic) of a specific Playlist.
     */
    public Playlists updatePlaylist(Playlists playlist, String newName, boolean newIsPublic) throws SQLException {
        String updatePlaylist = "UPDATE Playlists SET Name=?, IsPublic=? WHERE PlaylistId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePlaylist);
            updateStmt.setString(1, newName);
            updateStmt.setBoolean(2, newIsPublic);
            updateStmt.setInt(3, playlist.getPlaylistId());
            updateStmt.executeUpdate();

            // Update the playlist fields
            playlist.setName(newName);
            playlist.setIsPublic(newIsPublic);
            return playlist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (updateStmt != null) updateStmt.close();
            if (connection != null) connection.close();
        }
    }

    /**
     * Delete the specified Playlist instance from the database.
     */
    public Playlists delete(Playlists playlist) throws SQLException {
        String deletePlaylist = "DELETE FROM Playlists WHERE PlaylistId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePlaylist);
            deleteStmt.setInt(1, playlist.getPlaylistId());
            deleteStmt.executeUpdate();

            // Return null to indicate the playlist was deleted
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (deleteStmt != null) deleteStmt.close();
            if (connection != null) connection.close();
        }
    }
}