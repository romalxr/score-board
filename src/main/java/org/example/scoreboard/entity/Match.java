package org.example.scoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "Player1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2" , nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner" , nullable = false)
    private Player winner;
}
