package musicapplication.model;

/**
 * Represents a mood tag associated with a track in the music application.
 */
public class MoodTag {
    private int moodTagId;
    private Mood mood;  // Enum for mood
    private String trackId;
    private String userName;

    // Enum for predefined moods
    public enum Mood {
        HAPPY, SAD, RELAXED, EXCITED, ROMANTIC, ANGRY
    }

    /**
     * Constructs a new MoodTag object.
     *
     * @param moodTagId The unique identifier for the mood tag.
     * @param mood      The mood (e.g., HAPPY, SAD, RELAXED, etc.).
     * @param trackId   The identifier of the track associated with this mood tag.
     * @param userName  The username of the user who tagged the track with a mood.
     */
    public MoodTag(int moodTagId, Mood mood, String trackId, String userName) {
        this.moodTagId = moodTagId;
        this.mood = mood;
        this.trackId = trackId;
        this.userName = userName;
    }

    // Getters and setters

    public int getMoodTagId() {
        return moodTagId;
    }

    public void setMoodTagId(int moodTagId) {
        this.moodTagId = moodTagId;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
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
