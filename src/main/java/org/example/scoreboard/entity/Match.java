package org.example.scoreboard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Long id;

    private Player Player1;

    private org.example.scoreboard.entity.Player Player2;

    private org.example.scoreboard.entity.Player Winner;
}
