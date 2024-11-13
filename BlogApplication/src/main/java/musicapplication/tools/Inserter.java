package musicapplication.tools;

import musicapplication.dal.*;
import musicapplication.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * A class to demonstrate the CRUD functionality of all models.
 */
public class Inserter {

    public static void main(String[] args) throws SQLException {
        // Use Singleton DAOs
        UsersDao usersDao = UsersDao.getInstance();
        CommentsDao commentsDao = CommentsDao.getInstance();
        LikeAndDislikeDao likeAndDislikeDao = LikeAndDislikeDao.getInstance();
        ListeningHistoryDao listeningHistoryDao = ListeningHistoryDao.getInstance();
        MoodTagDao moodTagDao = MoodTagDao.getInstance();
        ArtistsDao artistsDao = ArtistsDao.getInstance();
        TrackArtistsDao trackArtistsDao = TrackArtistsDao.getInstance();

        // Create a user
        Users user = new Users("johndoe", "password123", "John", "Doe", "john@example.com", "1234567890");
        user = usersDao.create(user);
        System.out.println("Created user: " + user.getUserName());

        // Retrieve the user
        Users retrievedUser = usersDao.getUserByUserName("johndoe");
        System.out.println("Retrieved user: " + retrievedUser.getUserName());

        // Update the user
        retrievedUser.setEmail("newemail@example.com");
        usersDao.updateUser(retrievedUser);
        System.out.println("Updated user email: " + retrievedUser.getEmail());

        // Create a comment
        Comments comment = new Comments(0, new Timestamp(System.currentTimeMillis()), 
            "This is a great track!", user.getUserName(), "TRACK123");
        comment = commentsDao.create(comment);
        System.out.println("Created comment: " + comment.getCommentId());

        // Retrieve the comment
        //Comments retrievedComment = commentsDao.getCommentById(comment.getCommentId());
        //System.out.println("Retrieved comment: " + retrievedComment.getContent());

        // Get comments by user
        //List<Comments> userComments = commentsDao.getCommentsByUserName(user.getUserName());
        //System.out.println("Number of comments by user: " + userComments.size());

        // Create a like/dislike
        LikeAndDislike like = new LikeAndDislike(0, new Timestamp(System.currentTimeMillis()), 
            true, "TRACK123", user.getUserName());
        like = likeAndDislikeDao.create(like);
        System.out.println("Created like: " + like.getReviewId());

        // Retrieve the like/dislike
        LikeAndDislike retrievedLike = likeAndDislikeDao.getLikeAndDislikeById(like.getReviewId());
        System.out.println("Retrieved like: " + (retrievedLike.isLikeOrDislike() ? "Like" : "Dislike"));

        // Get likes/dislikes by user
        List<LikeAndDislike> userLikes = likeAndDislikeDao.getLikeAndDislikeByUserName(user.getUserName());
        System.out.println("Number of likes/dislikes by user: " + userLikes.size());

        // Update the like to a dislike
        retrievedLike.setLikeOrDislike(false);
        likeAndDislikeDao.updateLikeAndDislike(retrievedLike);
        System.out.println("Updated to dislike");

        // Create ListeningHistory entry
        ListeningHistory listeningHistory = new ListeningHistory(0, new Timestamp(System.currentTimeMillis()), 1, 180, "TRACK123", user.getUserName());
        listeningHistory = listeningHistoryDao.create(listeningHistory);
        System.out.println("Created listening history: " + listeningHistory.getHistoryId());

        // Retrieve ListeningHistory by ID
        ListeningHistory retrievedHistory = listeningHistoryDao.getListeningHistoryById(listeningHistory.getHistoryId());
        System.out.println("Retrieved listening history: " + retrievedHistory.getTrackId());

        // Create MoodTag entry (using the enum Mood)
        MoodTag moodTag = new MoodTag(0, MoodTag.Mood.HAPPY, "TRACK123", user.getUserName());
        moodTag = moodTagDao.create(moodTag);
        System.out.println("Created mood tag: " + moodTag.getMoodId());

        // Retrieve MoodTag by ID
        MoodTag retrievedMoodTag = moodTagDao.getMoodTagById(moodTag.getMoodId());
        System.out.println("Retrieved mood tag: " + retrievedMoodTag.getMood());

        // Get all mood tags for a track
        List<MoodTag> trackMoodTags = moodTagDao.getMoodTagsByTrackId("TRACK123");
        System.out.println("Number of mood tags for track: " + trackMoodTags.size());

        // Delete operations for ListeningHistory and MoodTag
        listeningHistoryDao.delete(retrievedHistory);
        System.out.println("Deleted listening history");

        moodTagDao.delete(retrievedMoodTag);
        System.out.println("Deleted mood tag");

        // Delete operations for LikeAndDislike, Comment, and User
        likeAndDislikeDao.delete(retrievedLike);
        System.out.println("Deleted like/dislike");

        //commentsDao.delete(retrievedComment);
        //System.out.println("Deleted comment");

        usersDao.delete(retrievedUser);
        System.out.println("Deleted user");
        
        
        // Artists
        // Create artists
        Artists artist1 = new Artists(1, "Gen Hoshino");
        artist1 = artistsDao.create(artist1);
        System.out.println("Created artist: " + artist1.getArtistName());

        Artists artist2 = new Artists(2, "Ben Woodward");
        artist2 = artistsDao.create(artist2);
        System.out.println("Created artist: " + artist2.getArtistName());

        // Retrieve the artist by ID
        Artists retrievedArtist1 = artistsDao.getArtistById(artist1.getArtistId());
        System.out.println("Retrieved artist by ID: " + retrievedArtist1.getArtistName());

        Artists retrievedArtist2 = artistsDao.getArtistById(artist2.getArtistId());
        System.out.println("Retrieved artist by ID: " + retrievedArtist2.getArtistName());

        // Retrieve artists by name (case-insensitive search)
        List<Artists> artistsByName = artistsDao.getArtistsByName("gen");
        System.out.println("Number of artists found by name 'gen': " + artistsByName.size());

        // Update the artist's name
        retrievedArtist1.setArtistName("Gen Hoshino - Updated");
        artistsDao.update(retrievedArtist1);
        System.out.println("Updated artist name to: " + retrievedArtist1.getArtistName());

        // Retrieve all artists
        List<Artists> allArtists = artistsDao.getAllArtists();
        System.out.println("Number of artists in the database: " + allArtists.size());

        // Delete the artists
        artistsDao.delete(retrievedArtist1);
        System.out.println("Deleted artist: " + retrievedArtist1.getArtistName());

        artistsDao.delete(retrievedArtist2);
        System.out.println("Deleted artist: " + retrievedArtist2.getArtistName());
        
        
        // TrackArtists
        // Create TrackArtist relationships
        TrackArtists trackArtist1 = new TrackArtists("TRACK123", artist1.getArtistId());
        trackArtist1 = trackArtistsDao.create(trackArtist1);
        System.out.println("Created TrackArtist for TrackId: " + trackArtist1.getTrackId() + " and ArtistId: " + trackArtist1.getArtistId());

        TrackArtists trackArtist2 = new TrackArtists("TRACK123", artist2.getArtistId());
        trackArtist2 = trackArtistsDao.create(trackArtist2);
        System.out.println("Created TrackArtist for TrackId: " + trackArtist2.getTrackId() + " and ArtistId: " + trackArtist2.getArtistId());

        // Retrieve TrackArtists by TrackId
        List<TrackArtists> trackArtistsByTrack = trackArtistsDao.getTrackArtistsByTrackId("TRACK123");
        System.out.println("Number of artists for TrackId 'TRACK123': " + trackArtistsByTrack.size());

        // Retrieve TrackArtists by ArtistId
        List<TrackArtists> trackArtistsByArtist = trackArtistsDao.getTrackArtistsByArtistId(artist1.getArtistId());
        System.out.println("Number of tracks for ArtistId '" + artist1.getArtistId() + "': " + trackArtistsByArtist.size());

        // Delete TrackArtist relationships
        trackArtistsDao.delete(trackArtist1);
        System.out.println("Deleted TrackArtist for TrackId: " + trackArtist1.getTrackId() + " and ArtistId: " + trackArtist1.getArtistId());

        trackArtistsDao.delete(trackArtist2);
        System.out.println("Deleted TrackArtist for TrackId: " + trackArtist2.getTrackId() + " and ArtistId: " + trackArtist2.getArtistId());


    }
    
    
}


