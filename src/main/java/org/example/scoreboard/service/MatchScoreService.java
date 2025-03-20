package org.example.scoreboard.service;

import org.example.scoreboard.entity.Match;
import org.example.scoreboard.entity.OngoingMatch;
import org.example.scoreboard.entity.Player;
import org.example.scoreboard.exception.NotFoundException;
import org.example.scoreboard.repository.OngoingMatchInMemoryRepository;
import org.example.scoreboard.repository.PlayerRepository;

public class MatchScoreService {
    private final MatchSevice matchSevice = new MatchSevice();
    private final OngoingMatchInMemoryRepository ongoingMatches = new OngoingMatchInMemoryRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    public String createNewMatch(String player1Name, String player2Name) {

        Player player1 = playerRepository.findByName(player1Name).orElse(
                playerRepository.save(new Player(null, player1Name))
        );
        Player player2 = playerRepository.findByName(player2Name).orElse(
                playerRepository.save(new Player(null, player2Name))
        );

        OngoingMatch newMatch = new OngoingMatch();
        newMatch.setPlayer1(player1);
        newMatch.setPlayer2(player2);
        newMatch = ongoingMatches.save(newMatch);

        return newMatch.getId();
    }

    public void updateScore(String id, int playerNumber) {
        OngoingMatch match = ongoingMatches.findById(id).orElseThrow(
                () -> new NotFoundException("Match with UUID '" + id + "' not found")
        );

        if (match.isFinished()) {
            return;
        }

        match.addScorePoints(playerNumber);
        if (match.isFinished()) {
            Match finishedMatch = new Match(null, match.getPlayer1(), match.getPlayer2(), match.getWinner());
            System.out.println("#2144 it's here");
            matchSevice.save(finishedMatch);
            System.out.println("#2145 it's here");
        }
    }

    public OngoingMatch getMatch(String id) {
        return ongoingMatches.findById(id).orElseThrow(
                () -> new NotFoundException("Match with UUID '" + id + "' not found")
        );
    }
}
