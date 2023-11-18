package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

    /**
     * Tests pickMove() by creating Moves and Players and checking the result.
     */
    @Test
    void testPickMove() {
        Player p1 = new Player();
        Player p2 = new Player(4, 6, 8, 7, 9, 2, 0);
        Move m1 = new Move(0, 5);
        Move m2 = new Move(5, 0);
        Move m3 = new Move(0, 9);
        Move m4 = new Move(8,0);

        assertTrue(m1.equals(p1.pickMove("PHYSICAL ATTACK")));
        assertTrue(m2.equals(p1.pickMove("MAGIC ATTACK")));
        assertTrue(m3.equals(p2.pickMove("PHYSICAL ATTACK")));
        assertTrue(m4.equals(p2.pickMove("MAGIC ATTACK")));
    }
}