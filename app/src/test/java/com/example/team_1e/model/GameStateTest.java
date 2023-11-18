package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameStateTest {
    @Test
    public void testAddOneProgress() {
        GameState gs = new GameState(2,3,4,"tutorial","player");

        assertEquals(3, gs.addOneProgress());
    }

    @Test
    public void testAddOneStoryProgress() {
        GameState gs = new GameState(2,3,4,"tutorial","player");

        assertEquals(4,gs.addOneStoryProgress());
    }
}
