package com.example.team_1e.model;

public class EasyMagicEnemy extends Enemy{
    /**
     * Constructor for EasyMagicEnemy objects.
     */
    public EasyMagicEnemy() {
        super(10, 10, 4, 4, 2, 2,2);
    }

    /**
     * A method that creates a Move object to represent a move created by an EasyMagicEnemy object.
     * Always picks a magic attack.
     * @return      a Move object representing the enemy's move
     */
    @Override
    public Move pickMove() {
        return new Move(this.magicAttack, 0);
    }
}
