package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HardMagicEnemyTest {
    /**
     * Tests HardMagicEnemy pickMove() by creating Enemies and Moves, having the Enemy pick a move, and checking result.
     */
    @Test
    void testPickMove() {
        HardMagicEnemy e1 = new HardMagicEnemy();
        Move m1 = new Move(e1.magicAttack, 0);
        Move m2 = new Move(0, e1.physicalAttack);
        Move m3 = e1.pickMove();

        assertTrue(m1.equals(m3) || m2.equals(m3));
    }
}