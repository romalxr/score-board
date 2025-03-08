package org.example.scoreboard.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.scoreboard.service.MatchScoreService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    MatchScoreService matchScoreService = new MatchScoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("#1232 it's here");

        RequestDispatcher view = req.getRequestDispatcher("new-match/index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("#4524 it's here");

        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");

        System.out.println("player1, player2 " + player1 + " " + player2);
        String id = matchScoreService.createNewMatch(player1, player2);
        resp.sendRedirect("../match-score?uuid=" + id);
    }
}
