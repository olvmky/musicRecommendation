package musicapplication.model;

import java.sql.Timestamp;

/**
 * Represents a user in the music application.
 * 
 * @author Jia Ying Li
 */
public class Playlists {
	protected int PlaylistId;
	protected Timestamp Created;
	protected boolean IsPublic;
	protected String UserName;
	protected String Name;
	public Playlists(int playlistId, Timestamp created, boolean isPublic, String userName, String name) {
		super();
		PlaylistId = playlistId;
		Created = created;
		IsPublic = isPublic;
		UserName = userName;
		Name = name;
	}
	public int getPlaylistId() {
		return PlaylistId;
	}
	public void setPlaylistId(int playlistId) {
		PlaylistId = playlistId;
	}
	public Timestamp getCreated() {
		return Created;
	}
	public void setCreated(Timestamp created) {
		Created = created;
	}
	public boolean isIsPublic() {
		return IsPublic;
	}
	public void setIsPublic(boolean isPublic) {
		IsPublic = isPublic;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
}