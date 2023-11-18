package com.example.team_1e.model;

public class HardPhysEnemy extends Enemy{
    /**
     * Constructor for HardPhysEnemy objects.
     */
    public HardPhysEnemy() {
        super(15, 15, 4, 4, 7, 7, 7);
    }

    /**
     * A method that creates a Move object to represent a move created by an HardPhysEnemy object.
     * Always picks a physical attack.
     * @return      a Move object representing the enemy's move
     */
    @Override
    public Move pickMove() {
        return new Move(0, this.physicalAttack);
    }
}
