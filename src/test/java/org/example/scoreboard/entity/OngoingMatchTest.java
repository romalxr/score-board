package org.example.scoreboard.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OngoingMatchTest {

    private OngoingMatch match;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player(0L, "Player 1");
        player2 = new Player(0L, "Player 2");
        match = new OngoingMatch();
        match.setId("1");
        match.setPlayer1(player1);
        match.setPlayer2(player2);
    }

    @Test
    void whenScoreIsDeuceAndPlayer1WinsPoint_thenGameNotFinished() {
        // Устанавливаем счет 40-40
        match.getPoints()[0] = 40;
        match.getPoints()[1] = 40;

        // Игрок 1 выигрывает очко
        match.addScorePoints(1);

        // Проверяем, что гейм не закончился
        assertEquals(0, match.getGames()[0]);
        assertEquals(0, match.getGames()[1]);
        assertTrue(match.getAds()[0]); // У игрока 1 должно быть преимущество
    }

    @Test
    void whenScoreIs40to0AndPlayer1WinsPoint_thenPlayer1WinsGame() {
        // Устанавливаем счет 40-0
        match.getPoints()[0] = 40;
        match.getPoints()[1] = 0;

        // Игрок 1 выигрывает очко
        match.addScorePoints(1);

        // Проверяем, что игрок 1 выиграл гейм
        assertEquals(1, match.getGames()[0]);
        assertEquals(0, match.getGames()[1]);
        // Проверяем, что очки сбросились
        assertEquals(0, match.getPoints()[0]);
        assertEquals(0, match.getPoints()[1]);
    }

    @Test
    void whenGamesAre6to6_thenTieBreakStarts() {
        // Устанавливаем счет геймов 6-6
        match.getGames()[0] = 6;
        match.getGames()[1] = 6;

        // Проверяем, что начался тайбрейк
        assertTrue(match.tieBreak());

        // Игрок 1 выигрывает очко в тайбрейке
        match.addScorePoints(1);
        assertEquals(1, match.getTieBreakPoints()[0]);
        assertEquals(0, match.getTieBreakPoints()[1]);

        // Игрок 2 выигрывает очко в тайбрейке
        match.addScorePoints(2);
        assertEquals(1, match.getTieBreakPoints()[0]);
        assertEquals(1, match.getTieBreakPoints()[1]);
    }

    @Test
    void whenTieBreakScoreIs7to5_thenPlayer1WinsSet() {
        // Устанавливаем счет геймов 6-6
        match.getGames()[0] = 6;
        match.getGames()[1] = 6;

        // Игрок 1 выигрывает 7 очков, игрок 2 - 5 очков
        match.getTieBreakPoints()[0] = 6;
        match.getTieBreakPoints()[1] = 5;
        match.addScorePoints(1);

        // Проверяем, что игрок 1 выиграл сет
        assertEquals(1, match.getSets()[0]);
        assertEquals(0, match.getSets()[1]);
        // Проверяем, что счет геймов и тайбрейка сбросился
        assertEquals(0, match.getGames()[0]);
        assertEquals(0, match.getGames()[1]);
        assertEquals(0, match.getTieBreakPoints()[0]);
        assertEquals(0, match.getTieBreakPoints()[1]);
    }

    @Test
    void whenPlayerWins2Sets_thenMatchIsFinished() {
        // Игрок 1 выигрывает 2 сета
        match.getSets()[0] = 2;

        assertTrue(match.isFinished());
        assertEquals(player1, match.getWinner());
        assertEquals(1, match.winnerNumber());
    }
}