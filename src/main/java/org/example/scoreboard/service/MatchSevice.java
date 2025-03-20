package org.example.scoreboard.service;

import org.example.scoreboard.entity.Match;
import org.example.scoreboard.repository.MatchRepository;

public class MatchSevice {
    MatchRepository matchRepository = new MatchRepository();

    public Match save(Match entity) {
        return matchRepository.save(entity);
    }
}
