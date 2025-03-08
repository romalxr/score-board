package org.example.scoreboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngoingMatch {

    private String id;

    private Player player1;
    private Player player2;

    private int[] sets = new int[2];
    private int[] games = new int[2];
    private int[] tieBreakPoints = new int[2];
    private int[] points = new int[2];
    private boolean[] ads = new boolean[2];

    public String getPoints(int playerNumber) {
        int playerIdx = playerNumber - 1;

        if (tieBreak()) {
            return "" + tieBreakPoints[playerIdx];
        } else if (ads[playerIdx]) {
            return "AD";
        } else {
            return "" + points[playerIdx];
        }
    }

    public String getGames(int playerNumber) {
        int playerIdx = playerNumber - 1;

        return "" + games[playerIdx];
    }

    public String getSets(int playerNumber) {
        int playerIdx = playerNumber - 1;

        return "" + sets[playerIdx];
    }

    public void addScorePoints(int playerNumber) {
        int playerIdx = playerNumber - 1;
        int otherIdx = 1 - playerIdx;

        if (tieBreak()) {
            tieBreakPoints[playerIdx]++;
            if (tieBreakPoints[playerIdx] >= 7 && tieBreakPoints[playerIdx] - tieBreakPoints[otherIdx] >= 2) {
                addSet(playerNumber);
            }
        } else if (pointsTie()) {
            if (ads[playerIdx]) {
                addGame(playerNumber);
            } else if (ads[otherIdx]) {
                ads[otherIdx] = false;
            } else {
                ads[playerIdx] = true;
            }
        } else {
            if (points[playerIdx] < 30) {
                points[playerIdx] += 15;
            } else if (points[playerIdx] < 40) {
                points[playerIdx] += 10;
            } else {
                addGame(playerNumber);
            }
        }
    }

    private void addGame(int playerNumber) {
        int playerIdx = playerNumber - 1;
        int otherIdx = 1 - playerIdx;

        games[playerIdx]++;
        if (games[playerIdx] >= 6 && games[playerIdx] - games[otherIdx] >= 2) {
            addSet(playerNumber);
        }

        points = new int[2];
        ads = new boolean[2];
    }

    private void addSet(int playerNumber) {
        int playerIdx = playerNumber - 1;

        sets[playerIdx]++;

        games = new int[2];
        tieBreakPoints = new int[2];
    }

    private boolean tieBreak() {
        return games[0] == 6 && games[1] == 6;
    }

    private boolean pointsTie() {
        return points[0] == 40 && points[1] == 40;
    }

    public boolean isFinished() {
        return sets[0] >= 2 || sets[1] >= 2;
    }

    public Integer winnerNumber() {
        if (!isFinished()) return null;
        return sets[0] >= 2 ? 1 : 2;
    }
}
