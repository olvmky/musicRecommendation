package musicapplication.tools;

import musicapplication.dal.*;
import musicapplication.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * A class to demonstrate the CRUD functionality of all models
 */
public class Inserter {

    public static void main(String[] args) throws SQLException {
        // CRUD for Users, Comments, LikeAndDislike, ListeningHistory, MoodTag
        UsersDao usersDao = new UsersDao();
        CommentsDao commentsDao = new CommentsDao();
        LikeAndDislikeDao likeAndDislikeDao = new LikeAndDislikeDao();
        ListeningHistoryDao listeningHistoryDao = new ListeningHistoryDao();
        MoodTagDao moodTagDao = new MoodTagDao();

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
        Comments retrievedComment = commentsDao.getCommentById(comment.getCommentId());
        System.out.println("Retrieved comment: " + retrievedComment.getContent());

        // Get comments by user
        List<Comments> userComments = commentsDao.getCommentsByUserName(user.getUserName());
        System.out.println("Number of comments by user: " + userComments.size());

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
        ListeningHistory listeningHistory = new ListeningHistory(0, "TRACK123", 
            user.getUserName(), new Timestamp(System.currentTimeMillis()));
        listeningHistory = listeningHistoryDao.create(listeningHistory);
        System.out.println("Created listening history: " + listeningHistory.getHistoryId());

        // Retrieve ListeningHistory by ID
        ListeningHistory retrievedHistory = listeningHistoryDao.getListeningHistoryById(listeningHistory.getHistoryId());
        System.out.println("Retrieved listening history: " + retrievedHistory.getTrackId());

        // Create MoodTag entry
        MoodTag moodTag = new MoodTag(0, "TRACK123", user.getUserName(), "Happy", new Timestamp(System.currentTimeMillis()));
        moodTag = moodTagDao.create(moodTag);
        System.out.println("Created mood tag: " + moodTag.getMoodTagId());

        // Retrieve MoodTag by ID
        MoodTag retrievedMoodTag = moodTagDao.getMoodTagById(moodTag.getMoodTagId());
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

        commentsDao.delete(retrievedComment);
        System.out.println("Deleted comment");

        usersDao.delete(retrievedUser);
        System.out.println("Deleted user");
    }
}
