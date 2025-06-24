package org.example.scoreboard.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.scoreboard.entity.Match;
import org.example.scoreboard.service.MatchService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = new MatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("#6623 it's here");

        String page = req.getParameter("page");
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        if (pageNumber < 1) pageNumber = 1;

        String filter = req.getParameter("filter_by_player_name");
        System.out.println("filter " + filter);

        List<Match> matchesPortion;
        if (filter != null && !filter.isEmpty()) {
            matchesPortion = matchService.findByPlayerName(pageNumber, filter);
        } else {
            matchesPortion = matchService.findAll(pageNumber);
        }

        String filterValue = filter == null ? "" : filter;
        req.setAttribute("filter", filterValue);
        req.setAttribute("matchesPortion", matchesPortion);
        req.setAttribute("hasLeft", true);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("hasRight", true);
        RequestDispatcher view = req.getRequestDispatcher("matches/index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("#2224 it's here");

        String playerName = req.getParameter("playerName");
        String pageNumber = req.getParameter("pageNumber");
        System.out.println("pageNumber" + pageNumber);

        resp.sendRedirect("matches?page=" + pageNumber + "&filter_by_player_name=" + playerName);
    }
}
