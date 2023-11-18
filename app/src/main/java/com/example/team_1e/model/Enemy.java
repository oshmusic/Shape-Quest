package com.example.team_1e.model;

public class Enemy extends APlayer{

    /**
     * Empty constructor for enemy with default values
     */
    public Enemy(){
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.magicAttack = 4;
        this.magicDefense = 3;
        this.physicalAttack = 4;
        this.physicalDefense = 3;
        this.image = 4;
    }

    /**
     * Constructor for objects of type Enemy
     * @param currentHealth the current health of the APlayer
     * @param maxHealth  the maximum health that the APlayer can have at this time
     * @param magicAttack  the APlayer's Magic Attack stat
     * @param magicDefense     the APlayer's Magic Defense stat
     * @param physicalAttack    the APlayer's Physical Attack stat
     * @param physicalDefense    the APlayer's Physical Defence stat
     * @param image              an integer representing the image associated with the APlayer
     */
    public Enemy(int currentHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack,
           int physicalDefense, int image) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.magicAttack = magicAttack;
        this.magicDefense = magicDefense;
        this.physicalAttack = physicalAttack;
        this.physicalDefense = physicalDefense;
        this.image = image;
    }

    /**
     * A method that creates a Move object to represent a move created by an enemy object. Currently,
     * the move is picked randomly. Later iterations will likely have more complex behavior.
     * @return      a Move object representing the enemy's move
     */
    public Move pickMove() {
        if(Math.random() < 0.5) {
            return new Move(this.magicAttack, 0);
        }
        else {
            return new Move(0, this.physicalAttack);
        }
    }
}
