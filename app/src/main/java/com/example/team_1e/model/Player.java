package com.example.team_1e.model;

/**
 * Represents a player character controlled by the user
 */
public class Player extends APlayer {
    /**
     * Empty constructor. Gives Player objects default stats when used.
     */
    public Player() {
        super(35,35,5,5,5,5,1);
    }

    /**
     * Constructor for objects of type Player
     * @param currentHealth     the current health stat of the Player
     * @param maxHealth         the maximum health that the Player can have at this time
     * @param magicAttack       the Player's Magic Attack stat
     * @param magicDefense      the Player's Magic Defense stat
     * @param physicalAttack    the Player's Physical Attack stat
     * @param physicalDefense   the Player's Physical Defense stat
     * @param image             an integer to represent the image associated with the Player
     */

    public Player(int currentHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack,
           int physicalDefense, int image) {
        super(currentHealth, maxHealth, magicAttack, magicDefense, physicalAttack, physicalDefense, image);
    }

    // Creates human player's move.

    /**
     * A method that creates a Move object to represent a move selected by a user
     * @param s     the user's move selection
     * @return      a Move object representing the user's choice
     */
    public Move pickMove(String s) {
        if (s.compareTo("PHYSICAL ATTACK") == 0) {
            return new Move(0, this.physicalAttack);
        } else if (s.compareTo("MAGIC ATTACK") == 0) {
            return new Move(this.magicAttack, 0);
        } else {
            return null;
        }
    }
}
