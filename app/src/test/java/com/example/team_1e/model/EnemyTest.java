package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnemyTest {

    /**
     * Tests Enemy pickMove() by creating Enemies and Moves, having the Enemy pick a move, and checking result.
     */
    @Test
    void testPickMove() {
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(3,6,7,7,7,7,0);
        Move m1 = new Move(e1.magicAttack, 0);
        Move m2 = new Move(0, e1.physicalAttack);
        Move m3 = new Move(e2.magicAttack, 0);
        Move m4 = new Move(0, e2.physicalAttack);
        Move m5 = e1.pickMove();
        Move m6 = e2.pickMove();

        assertTrue(m1.equals(m5) || m2.equals(m5));
        assertTrue(m3.equals(m6) || m4.equals(m6));
    }
}