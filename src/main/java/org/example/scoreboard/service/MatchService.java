package org.example.scoreboard.service;

import org.example.scoreboard.entity.Match;
import org.example.scoreboard.repository.MatchRepository;

import java.util.ArrayList;
import java.util.List;

public class MatchService {
    static final int DEFAULT_PAGE_SIZE = 3;
    MatchRepository matchRepository = new MatchRepository();

    public Match save(Match entity) {
        return matchRepository.save(entity);
    }

    public List<Match> findAll(int pageNumber) {
        return new ArrayList<>(matchRepository.findAllPageable(pageNumber, DEFAULT_PAGE_SIZE));
    }

    public List<Match> findByPlayerName(int pageNumber, String playerName) {
        return new ArrayList<>(matchRepository.findByPlayerNamePageable(pageNumber, DEFAULT_PAGE_SIZE, playerName));
    }
}
