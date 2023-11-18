package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveTest {

    /**
     * Tests getMagicAttack() by creating Moves and checking result.
     */
    @Test
    void testGetMagicAttack() {
        Move m1 = new Move(3, 0);
        Move m2 = new Move(0, 5);
        Move m3 = new Move(6, 7);

        assertEquals(m1.magicAttack, m1.getMagicAttack());
        assertEquals(m2.magicAttack, m2.getMagicAttack());
        assertEquals(m3.magicAttack, m3.getMagicAttack());
    }

    /**
     * Tests getPhysicalAttack() by creating Moves and checking result.
     */
    @Test
    void testGetPhysicalAttack() {
        Move m1 = new Move(4,0);
        Move m2 = new Move(0,8);
        Move m3 = new Move(2,10);

        assertEquals(m1.physicalAttack, m1.getPhysicalAttack());
        assertEquals(m2.physicalAttack, m2.getPhysicalAttack());
        assertEquals(m3.physicalAttack, m3.getPhysicalAttack());
    }

    /**
     * Test Move equals() by creating moves and checking result.
     */
    @Test
    void testEquals() {
        Move m1 = new Move(4, 0);
        Move m2 = new Move(4, 0);
        Move m3 = new Move(0, 5);
        Move m4 = new Move(0, 5);
        Move m5 = new Move(4, 9);
        Move m6 = new Move(4, 9);

        assertTrue(m1.equals(m2));
        assertFalse(m1.equals(m3));
        assertTrue(m3.equals(m4));
        assertTrue(m4.equals(m3));
        assertFalse(m2.equals(m6));
        assertTrue(m5.equals(m6));
    }
}