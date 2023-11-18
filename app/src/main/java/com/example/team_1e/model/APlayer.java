package com.example.team_1e.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public abstract class APlayer implements Serializable {
    /**
     * The current health stat for APlayer and subclass objects
     */
    int currentHealth;
    /**
     * The max health stat for APlayer and subclass objects
     */
    int maxHealth;
    /**
     * The current magic attack for APlayer and subclass objects
     */
    int magicAttack;
    /**
     * The current magic defense for APlayer and subclass objects
     */
    int magicDefense;
    /**
     * The current physical attack for APlayer and subclass objects
     */
    int physicalAttack;
    /**
     * The current physical defense for APlayer and subclass objects
     */
    int physicalDefense;

    /**
     * An integer representing the image associated with the APlayer object
     */
    int image;

    /**
     * Empty constructor
     */
    APlayer() {}

    // constructor
    /**
     * Constructor for objects of type APlayer
     * @param currHealth the current health of the APlayer
     * @param maxHealth  the maximum health that the APlayer can have at this time
     * @param magAtk     the APlayer's Magic Attack stat
     * @param magDef     the APlayer's Magic Defense stat
     * @param physAtk    the APlayer's Physical Attack stat
     * @param physDef    the APlayer's Physical Defence stat
     */
    APlayer(int currHealth, int maxHealth, int magAtk, int magDef, int physAtk, int physDef, int image) {
        this.currentHealth = currHealth;
        this.maxHealth = maxHealth;
        this.magicAttack = magAtk;
        this.magicDefense = magDef;
        this.physicalAttack = physAtk;
        this.physicalDefense = physDef;
        this.image = image;
    }

    // method for resolving move

    /**
     * A method that takes in a move and the APlayer that the move is applied to. Changes the APlayer's
     * stats based on the damage done by the move.
     * @param m     the move in question (represented as a Move object)
     * @param a     the APlayer object that the move is being applied to
     * @return      the damage that the APlayer object a takes as a result of the move m
     */
    public int resolveMove(Move m, APlayer a) {
        int damage = 0;
        if (m.getMagicAttack() == 0) {
            damage = 5 + m.getPhysicalAttack() - a.physicalDefense;
            if (damage <= 0) {damage = 1;}
            a.currentHealth -= damage;
        } else if (m.getPhysicalAttack() == 0) {
            damage = 5 + m.getMagicAttack() - a.magicDefense;
            if (damage <= 0) {damage = 1;}
            a.currentHealth -= damage;
        } return damage;
    }

    /**
     * Returns a textual representation of the APlayer, comprised of a list of the APlayer's
     * current stats.
     * @return  the APlayer's textual representation
     *
     */
    @Override
    @NonNull
    public String toString(){
        return "Health: " + this.currentHealth +"/" + this.maxHealth + "\nMagic Attack: " + this.magicAttack + "\nMagic Defense: " + this.magicDefense
                + "\nPhysical Attack: " + this.physicalAttack + "\nPhysical Defense: " + this.physicalDefense;
    }


    /**
     * Returns the APlayer's current health stat (used elsewhere for determining if a fight has completed)
     * @return the APlayer's currentHealth stat
     */
    public int getCurrentHealth(){
        return this.currentHealth;
    }

    /**
     * A method to increase the Player's physical attack stat
     * @param upgradeValue The amount the stat should be increased by
     * @return The new physical attack stat
     */
    public int upgradePhysicalAttack (int upgradeValue) {return this.physicalAttack += upgradeValue; }

    /**
     * A method to increase the Player's physical defense stat
     * @param upgradeValue The amount the stat should be increased by
     * @return The new physical defense stat
     */
    public int upgradePhysicalDefense (int upgradeValue) { return this.physicalDefense += upgradeValue; }

    /**
     * A method to increase the Player's magic attack stat
     * @param upgradeValue The amount the stat should be increased by
     * @return The new magic attack stat
     */
    public int upgradeMagicAttack (int upgradeValue) { return this.magicAttack += upgradeValue; }

    /**
     * A method to increase the Player's magic defense stat
     * @param upgradeValue The amount the stat should be increased by
     * @return The new magic defense stat
     */
    public int upgradeMagicDefense (int upgradeValue) { return this.magicDefense += upgradeValue; }

    /**
     * A method to increase the Player's current health stat
     * @param upgradeValue The amount the stat should be increased by (unless this would result in
     *                     the current health being greater than the max health, in which case the
     *                     current health should be increased to equal the max health)
     * @return The new physical attack stat
     */
    public int upgradeCurrentHealth (int upgradeValue) {
        if (this.currentHealth + upgradeValue > this.maxHealth) {
            return this.currentHealth = this.maxHealth;
        }
        else { return this.currentHealth += upgradeValue; }
    }

    /**
     * A method to increase the Player's max health stat
     * @param upgradeValue The amount the stat should be increased by
     * @return The new max health stat
     */
    public int upgradeMaxHealth (int upgradeValue) {
        return this.maxHealth += upgradeValue;
    }

    /**
     * A method that returns the integer tracking the image associated with this APlayer object
     * @return The APlayer object's image tracker
     */
    public int getImageTracker(){
        return this.image;
    }

    /**
     * A method that returns the APlayer object's Physical Attack stat
     * @return The APlayer's Physical Attack stat
     */
    public int getPhysicalAttack(){
        return this.physicalAttack;
    }

    /**
     * A method that returns the APlayer object's Physical Defense stat
     * @return The APlayer's Physical Defense stat
     */
    public int getPhysicalDefense() {
        return physicalDefense;
    }

    /**
     * A method that returns the APlayer object's Magic Attack stat
     * @return The APlayer's Magic Attack stat
     */
    public int getMagicAttack() {
        return magicAttack;
    }

    /**
     * A method that returns the APlayer object's Magic Defense stat
     * @return The APlayer's Magic Defense stat
     */
    public int getMagicDefense() {
        return magicDefense;
    }

    /**
     * A method that returns the APlayer object's Max Health stat
     * @return The APlayer's Max Health stat
     */
    public int getMaxHealth() {
        return maxHealth;
    }
}