package org.example.scoreboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Long id;

    private Player player1;

    private org.example.scoreboard.entity.Player player2;

    private org.example.scoreboard.entity.Player winner;
}
