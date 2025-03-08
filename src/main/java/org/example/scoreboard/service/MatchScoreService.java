package org.example.scoreboard.service;

import org.example.scoreboard.entity.OngoingMatch;
import org.example.scoreboard.entity.Player;
import org.example.scoreboard.exception.NotFoundException;
import org.example.scoreboard.repository.OngoingMatchInMemoryRepository;

public class MatchScoreService {

    OngoingMatchInMemoryRepository ongoingMatches = new OngoingMatchInMemoryRepository();

    public String createNewMatch(String player1Name, String player2Name) {
        OngoingMatch newMatch = new OngoingMatch();
        newMatch.setPlayer1(new Player(3L, player1Name));
        newMatch.setPlayer2(new Player(5L, player2Name));
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
            // ongoingMatches.delete(match);
            // save match to completed matches
        }
    }

    public OngoingMatch getMatch(String id) {
        return ongoingMatches.findById(id).orElseThrow(
                () -> new NotFoundException("Match with UUID '" + id + "' not found")
        );
    }
}
