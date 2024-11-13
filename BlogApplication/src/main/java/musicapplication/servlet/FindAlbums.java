package musicapplication.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicapplication.dal.*;
import musicapplication.model.*;

@WebServlet("/findalbums")
public class FindAlbums extends HttpServlet {

	protected AlbumsDao albumsDao;

	@Override
	public void init() throws ServletException {
		albumsDao = AlbumsDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("albummessages", messages);

		List<Albums> albums = new ArrayList<Albums>();

		String name = req.getParameter("albumtitle");
		if (name == null || name.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			try {
				albums = albumsDao.getAlbumsByName(name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + name);
		}
		req.setAttribute("albums", albums);

		// Set the active tab
		req.setAttribute("activeTab", "albums");

		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
