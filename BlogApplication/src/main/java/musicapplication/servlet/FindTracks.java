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

@WebServlet("/findtracks")
public class FindTracks extends HttpServlet {

	protected TracksDao tracksDao;

	@Override
	public void init() throws ServletException {
		tracksDao = TracksDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("trackmessages", messages);

		List<Tracks> tracks = new ArrayList<Tracks>();

		String name = req.getParameter("tracktitle");
		if (name == null || name.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			try {
				tracks = tracksDao.getTracksByName(name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + name);
		}
		req.setAttribute("tracks", tracks);

		req.getRequestDispatcher("/Home.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
