package org.example.scoreboard.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.scoreboard.entity.OngoingMatch;
import org.example.scoreboard.exception.NotFoundException;
import org.example.scoreboard.service.MatchScoreService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    MatchScoreService matchScoreService = new MatchScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("#3123 it's here");

        String id = req.getParameter("uuid");
        System.out.println("uuid " + id);

        OngoingMatch currentMatch = matchScoreService.getMatch(id);
        req.setAttribute("currentMatch", currentMatch);
        RequestDispatcher view = req.getRequestDispatcher("match-score/index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("#7143 it's here");

        String id = req.getParameter("uuid");
        String winnerInfo = req.getParameter("winnerInfo");
        int playerNumber = winnerInfo.equals("player-1-win") ? 1 : 2;
        System.out.println("winnerInfo " + winnerInfo);

        try {
            matchScoreService.updateScore(id, playerNumber);
        } catch (Exception e) {
            System.out.println("#7145 it's here");
            System.out.println(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

        System.out.println("#7144 it's here");
        resp.sendRedirect("match-score?uuid=" + id);
    }
}
