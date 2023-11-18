package com.example.team_1e.model;

import java.io.Serializable;

public class Move implements Serializable {
    /**
     * Represents the magic attack stat of the move
     */
    int magicAttack;
    /**
     * Represents the physical attack stat of the move
     */
    int physicalAttack;

    /**
     * A constructor for Move objects.
     * @param magAtk the magicAttack stat of the Player or Enemy that created the move
     * @param physAtk the physicalAttack stat of the Player or Enemy that created the move
     */
    public Move(int magAtk, int physAtk){
        this.magicAttack = magAtk;
        this.physicalAttack = physAtk;
    }

    //Methods to access the field values
    /**
    * A method to get the magicAttack value of the Move.
    * @return the magicAttack value of the Move
    */
    public int getMagicAttack(){
        return this.magicAttack;
    }

    /**
     * A method to get the physicalAttack value of the Move.
     * @return the physicalAttack value of the Move
     */
    public int getPhysicalAttack(){
        return this.physicalAttack;
    }

    /**
     * A custom equals method for Move objects.
     * @param o The object this move should be compared to
     * @return A boolean stating if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Move)) {
            return false;
        }

        Move m = (Move) o;

        return (this.magicAttack == m.magicAttack) && (this.physicalAttack == m.physicalAttack);
    }


}
