package com.example.team_1e.model;

public class HardMagicEnemy extends Enemy{
    /**
     * Constructor for HardMagicEnemy objects.
     */
    public HardMagicEnemy() {
        super(15, 15, 7, 7, 4, 4, 6);
    }

    /**
     * A method that creates a Move object to represent a move created by an HardMagicEnemy object.
     * Always picks a magic attack.
     * @return      a Move object representing the enemy's move
     */
    @Override
    public Move pickMove() {
        return new Move(this.magicAttack, 0);
    }
}
