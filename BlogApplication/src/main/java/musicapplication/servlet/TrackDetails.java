package musicapplication.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicapplication.dal.*;
import musicapplication.model.*;

@WebServlet("/trackdetails")
public class TrackDetails extends HttpServlet {

    protected TracksDao tracksDao;
    protected MoodTagDao moodTagDao;
    protected LikeAndDislikeDao likeAndDislikeDao;
    protected CommentsDao commentsDao;

    @Override
    public void init() throws ServletException {
        tracksDao = TracksDao.getInstance();
        moodTagDao = MoodTagDao.getInstance();
        likeAndDislikeDao = LikeAndDislikeDao.getInstance();
        commentsDao = CommentsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackid");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            // Fetch track details
            Tracks track = tracksDao.getTrackById(trackId);
            if (track == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Track not found");
                return;
            }

            // Fetch mood tags for the track
            List<MoodTag> moodTags = moodTagDao.getMoodTagsByTrackId(trackId);
            Map<MoodTag.Mood, Integer> moodTagCount = new HashMap<>();
            for (MoodTag moodTag : moodTags) {
                moodTagCount.put(moodTag.getMood(), moodTagCount.getOrDefault(moodTag.getMood(), 0) + 1);
            }

            // Fetch likes and dislikes for the track
            List<LikeAndDislike> likeAndDislikeList = likeAndDislikeDao.getLikeAndDislikeByTrackId(trackId);
            int likeCount = 0;
            int dislikeCount = 0;
            for (LikeAndDislike likeAndDislike : likeAndDislikeList) {
                if (likeAndDislike.equals(true)) {
                	likeCount++;
                } else {
                    dislikeCount++;
                }
            }

            // Fetch comments for the track
            List<Comments> comments = commentsDao.getCommentsByTrackId(trackId);

            // Set attributes to be accessed in the JSP
            req.setAttribute("track", track);
            req.setAttribute("moodTags", moodTags);
            req.setAttribute("moodTagCount", moodTagCount);
            req.setAttribute("likeCount", likeCount);
            req.setAttribute("dislikeCount", dislikeCount);
            req.setAttribute("comments", comments);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.getRequestDispatcher("/TrackDetails.jsp").forward(req, resp);
    }
}
