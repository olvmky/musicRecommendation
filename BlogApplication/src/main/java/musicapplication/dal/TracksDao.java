package musicapplication.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import musicapplication.model.*;

public class TracksDao {
	protected ConnectionManager connectionManager;

	private static TracksDao instance = null;
	protected TracksDao() {
		connectionManager = new ConnectionManager();
	}
	public static TracksDao getInstance() {
		if (instance == null) {
			instance = new TracksDao();
		}
		return instance;
	}

	public Tracks create(Tracks track) throws SQLException {
		String insertTrack = "INSERT INTO Tracks(TrackId, TrackName, AlbumId, GenreId, Popularity, DurationMs, Explicit, Danceability, Energy, Pitch, Loudness, Modality, Speechiness, Acousticness, Instrumentalness, Liveness, Valence, Tempo, TimeSignature) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTrack);
			insertStmt.setString(1, track.getTrackId());
			insertStmt.setString(2, track.getTrackName());
			insertStmt.setInt(3, track.getAlbumId());
			insertStmt.setInt(4, track.getGenreId());
			insertStmt.setInt(5, track.getPopularity());
			insertStmt.setInt(6, track.getDurationMs());
			insertStmt.setBoolean(7, track.isExplicit());
			insertStmt.setDouble(8, track.getDanceability());
			insertStmt.setDouble(9, track.getEnergy());
			insertStmt.setInt(10, track.getPitch());
			insertStmt.setDouble(11, track.getLoudness());
			insertStmt.setInt(12, track.getModality());
			insertStmt.setDouble(13, track.getSpeechiness());
			insertStmt.setDouble(14, track.getAcousticness());
			insertStmt.setDouble(15, track.getInstrumentalness());
			insertStmt.setDouble(16, track.getLiveness());
			insertStmt.setDouble(17, track.getValence());
			insertStmt.setDouble(18, track.getTempo());
			insertStmt.setInt(19, track.getTimeSignature());
			insertStmt.executeUpdate();

			return track;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) connection.close();
			if (insertStmt != null) insertStmt.close();
		}
	}

	public Tracks getTrackById(String trackId) throws SQLException {
		String selectTrack = "SELECT TrackId, TrackName, AlbumId, GenreId, Popularity, DurationMs, Explicit, Danceability, Energy, Pitch, Loudness, Modality, Speechiness, Acousticness, Instrumentalness, Liveness, Valence, Tempo, TimeSignature FROM Tracks WHERE TrackId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTrack);
			selectStmt.setString(1, trackId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String resultTrackId = results.getString("TrackId");
				String trackName = results.getString("TrackName");
				int albumId = results.getInt("AlbumId");
				int genreId = results.getInt("GenreId");
				int popularity = results.getInt("Popularity");
				int durationMs = results.getInt("DurationMs");
				boolean explicit = results.getBoolean("Explicit");
				double danceability = results.getDouble("Danceability");
				double energy = results.getDouble("Energy");
				int pitch = results.getInt("Pitch");
				double loudness = results.getDouble("Loudness");
				int modality = results.getInt("Modality");
				double speechiness = results.getDouble("Speechiness");
				double acousticness = results.getDouble("Acousticness");
				double instrumentalness = results.getDouble("Instrumentalness");
				double liveness = results.getDouble("Liveness");
				double valence = results.getDouble("Valence");
				double tempo = results.getDouble("Tempo");
				int timeSignature = results.getInt("TimeSignature");

				Tracks track = new Tracks(resultTrackId, trackName, albumId, genreId, popularity, durationMs, explicit, danceability, energy, pitch, loudness, modality, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
				return track;
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
	
	public List<Tracks> getTracksByName(String name) throws SQLException {
	    List<Tracks> tracks = new ArrayList<>();
	    String selectTracks = "SELECT TrackId, TrackName, AlbumId, GenreId, Popularity, DurationMs, Explicit, Danceability, Energy, Pitch, Loudness, Modality, Speechiness, Acousticness, Instrumentalness, Liveness, Valence, Tempo, TimeSignature FROM Tracks WHERE TrackName LIKE ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectTracks);
	        selectStmt.setString(1, "%" + name + "%"); // Using wildcards for partial matching
	        results = selectStmt.executeQuery();

	        while (results.next()) {
	            String trackId = results.getString("TrackId");
	            String trackName = results.getString("TrackName");
	            int albumId = results.getInt("AlbumId");
	            int genreId = results.getInt("GenreId");
	            int popularity = results.getInt("Popularity");
	            int durationMs = results.getInt("DurationMs");
	            boolean explicit = results.getBoolean("Explicit");
	            double danceability = results.getDouble("Danceability");
	            double energy = results.getDouble("Energy");
	            int pitch = results.getInt("Pitch");
	            double loudness = results.getDouble("Loudness");
	            int modality = results.getInt("Modality");
	            double speechiness = results.getDouble("Speechiness");
	            double acousticness = results.getDouble("Acousticness");
	            double instrumentalness = results.getDouble("Instrumentalness");
	            double liveness = results.getDouble("Liveness");
	            double valence = results.getDouble("Valence");
	            double tempo = results.getDouble("Tempo");
	            int timeSignature = results.getInt("TimeSignature");

	            Tracks track = new Tracks(trackId, trackName, albumId, genreId, popularity, durationMs, explicit, danceability, energy, pitch, loudness, modality, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
	            tracks.add(track);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) connection.close();
	        if (selectStmt != null) selectStmt.close();
	        if (results != null) results.close();
	    }
	    return tracks;
	}

	public List<Tracks> getTracksByGenre(int genreId) throws SQLException {
		List<Tracks> tracks = new ArrayList<>();
		String selectTracks = "SELECT TrackId, TrackName, AlbumId, GenreId, Popularity, DurationMs, Explicit, Danceability, Energy, Pitch, Loudness, Modality, Speechiness, Acousticness, Instrumentalness, Liveness, Valence, Tempo, TimeSignature FROM Tracks WHERE GenreId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTracks);
			selectStmt.setInt(1, genreId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				String trackId = results.getString("TrackId");
				String trackName = results.getString("TrackName");
				int albumId = results.getInt("AlbumId");
				int resultGenreId = results.getInt("GenreId");
				int popularity = results.getInt("Popularity");
				int durationMs = results.getInt("DurationMs");
				boolean explicit = results.getBoolean("Explicit");
				double danceability = results.getDouble("Danceability");
				double energy = results.getDouble("Energy");
				int pitch = results.getInt("Pitch");
				double loudness = results.getDouble("Loudness");
				int modality = results.getInt("Modality");
				double speechiness = results.getDouble("Speechiness");
				double acousticness = results.getDouble("Acousticness");
				double instrumentalness = results.getDouble("Instrumentalness");
				double liveness = results.getDouble("Liveness");
				double valence = results.getDouble("Valence");
				double tempo = results.getDouble("Tempo");
				int timeSignature = results.getInt("TimeSignature");

				Tracks track = new Tracks(trackId, trackName, albumId, resultGenreId, popularity, durationMs, explicit, danceability, energy, pitch, loudness, modality, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
				tracks.add(track);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) connection.close();
			if (selectStmt != null) selectStmt.close();
			if (results != null) results.close();
		}
		return tracks;
	}

	public Tracks delete(Tracks track) throws SQLException {
		String deleteTrack = "DELETE FROM Tracks WHERE TrackId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTrack);
			deleteStmt.setString(1, track.getTrackId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) connection.close();
			if (deleteStmt != null) deleteStmt.close();
		}
	}
	
	public List<Tracks> getTracksForPlaylist(int playlistId) throws SQLException {
	    List<Tracks> tracks = new ArrayList<>();
	    String selectTracks = "SELECT t.* FROM Tracks t JOIN PlaylistTracks pt ON t.TrackId = pt.Track_Id WHERE pt.PlayListId = ? ORDER BY pt.Position;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectTracks);
	        selectStmt.setInt(1, playlistId);
	        results = selectStmt.executeQuery();
	        while (results.next()) {
	            String trackId = results.getString("TrackId");
	            String trackName = results.getString("TrackName");
	            int albumId = results.getInt("AlbumId");
	            int genreId = results.getInt("GenreId");
	            int popularity = results.getInt("Popularity");
	            int durationMs = results.getInt("DurationMs");
	            boolean explicit = results.getBoolean("Explicit");
	            double danceability = results.getDouble("Danceability");
	            double energy = results.getDouble("Energy");
	            int pitch = results.getInt("Pitch");
	            double loudness = results.getDouble("Loudness");
	            int modality = results.getInt("Modality");
	            double speechiness = results.getDouble("Speechiness");
	            double acousticness = results.getDouble("Acousticness");
	            double instrumentalness = results.getDouble("Instrumentalness");
	            double liveness = results.getDouble("Liveness");
	            double valence = results.getDouble("Valence");
	            double tempo = results.getDouble("Tempo");
	            int timeSignature = results.getInt("TimeSignature");

	            Tracks track = new Tracks(trackId, trackName, albumId, genreId, popularity, durationMs, explicit, danceability, energy, pitch, loudness, modality, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
	            tracks.add(track);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) connection.close();
	        if (selectStmt != null) selectStmt.close();
	        if (results != null) results.close();
	    }
	    return tracks;
	}
}