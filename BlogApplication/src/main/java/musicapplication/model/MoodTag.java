package musicapplication.model;

/**
 * Represents a mood tag associated with a track in the music application.
 */
public class MoodTag {
    private int moodId;
    private String mood;
    private String trackId;
    private String userName;

    /**
     * Constructs a new MoodTag object.
     *
     * @param moodId   The unique identifier for the mood tag.
     * @param mood     The mood (e.g., HAPPY, SAD, RELAXED, etc.).
     * @param trackId  The identifier of the track associated with this mood tag.
     * @param userName The username of the user who tagged the track with a mood.
     */
    public MoodTag(int moodId, String mood, String trackId, String userName) {
        this.moodId = moodId;
        this.mood = mood;
        this.trackId = trackId;
        this.userName = userName;
    }

    // Getters and setters

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
