package src;

/**
 * Represents a living entity in the game world (either a Player or an Enemy)
 */
public abstract class APlayer {
    //Instance variables for all subclasses of src.APlayer
    int currentHealth;
    int maxHealth;
    int magicAttack;
    int magicDefense;
    int physicalAttack;
    int physicalDefense;

    // empty constructor

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
    APlayer(int currHealth, int maxHealth, int magAtk, int magDef, int physAtk, int physDef) {
        this.currentHealth = currHealth;
        this.maxHealth = maxHealth;
        this.magicAttack = magAtk;
        this.magicDefense = magDef;
        this.physicalAttack = physAtk;
        this.physicalDefense = physDef;
    }

    // abstract method for resolving move

    /**
     * An abstract method to be implemented by subclasses. Calculates the damage of a given move
     * when applied to a given target.
     * @param m     the move in question (represented as a Move object)
     * @param a     the APlayer object that the move is being applied to
     * @return      the damage that the APlayer object a takes as a result of the move m
     */
    public abstract int resolveMove(Move m, APlayer a);

    /**
     * Returns a textual representation of the APlayer, comprised of a list of the APlayer's
     * current stats.
     * @return  the APlayer's textual representation
     */
    public String toString(){
        return "Health: " + this.currentHealth +"/" + this.maxHealth + "\nMagic Attack: " + this.magicAttack + "\nMagic Defense: " + this.magicDefense
                + "\nPhysical Attack: " + this.physicalAttack + "\nPhysical Defense: " + this.physicalDefense;
    }
}