package musicapplication.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicapplication.dal.*;
import musicapplication.model.*;

@WebServlet("/userdetails")
public class UserDetails extends HttpServlet {
    
    protected UsersDao usersDao;
    protected ListeningHistoryDao listeningHistoryDao;
    protected PlaylistsDao playlistsDao;
    
    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
        listeningHistoryDao = ListeningHistoryDao.getInstance();
        playlistsDao = PlaylistsDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userName = req.getParameter("username");
        
        try {
            Users user = usersDao.getUserByUserName(userName);
            List<ListeningHistory> listeningHistory = listeningHistoryDao.getListeningHistoryForUser(userName);
            List<Playlists> playlists = playlistsDao.getPlaylistsForUser(userName);
            
            req.setAttribute("user", user);
            req.setAttribute("listeningHistory", listeningHistory);
            req.setAttribute("playlists", playlists);
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.getRequestDispatcher("/UserDetails.jsp").forward(req, resp);
    }
}
