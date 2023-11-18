package com.example.team_1e.model;

public class EasyPhysEnemy extends Enemy{
    /**
     * Constructor for EasyPhysEnemy objects.
     */
    public EasyPhysEnemy() {
        super(10, 10, 2, 2, 4, 4, 3);
    }

    /**
     * A method that creates a Move object to represent a move created by an EasyPhysEnemy object.
     * Always picks a physical attack.
     * @return      a Move object representing the enemy's move
     */
    @Override
    public Move pickMove() {
        return new Move(0, this.physicalAttack);
    }
}
