package src;

public class Player extends APlayer {
    // Empty constructor for player with default values
    Player() {
        this.currentHealth = 20;
        this.maxHealth = 20;
        this.magicAttack = 5;
        this.magicDefense = 5;
        this.physicalAttack = 5;
        this.physicalDefense = 5;
    }

    // Constructor for src.Player
    Player(int currentHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack,
           int physicalDefense) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.magicAttack = magicAttack;
        this.magicDefense = magicDefense;
        this.physicalAttack = physicalAttack;
        this.physicalDefense = physicalDefense;
    }

    // Creates human player's move.
    public Move pickMove(String s) {
        if (s.compareTo("PHYSICAL ATTACK") == 0) {
            return new Move(0, this.physicalAttack);
        } else if (s.compareTo("MAGIC ATTACK") == 0) {
            return new Move(this.magicAttack, 0);
        } else {
            return null;
        }
    }

    /*
     * A method that takes in a move and an enemy that the move is applied to. The method also changes the
     * enemy's stats based on the damage done by the move.
     * It is separate from resolving a move made by an enemy to establish a structure that allows later
     * iterations to resolve player and enemy moves differently.
     */
    @Override
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
        }
        return damage;
    }
}
