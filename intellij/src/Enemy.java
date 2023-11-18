package src;

public class Enemy extends APlayer{

    // Empty constructor for enemy with default values
    public Enemy(){
        this.currentHealth = 10;
        this.maxHealth = 10;
        this.magicAttack = 2;
        this.magicDefense = 2;
        this.physicalAttack = 2;
        this.physicalDefense = 2;
    }

    // Constructor for enemy
    Enemy(int currentHealth, int maxHealth, int magicAttack, int magicDefense, int physicalAttack,
           int physicalDefense) {
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.magicAttack = magicAttack;
        this.magicDefense = magicDefense;
        this.physicalAttack = physicalAttack;
        this.physicalDefense = physicalDefense;
    }

    // Creates enemy's move. In this iteration, it is random. Later iterations will have more complex behavior.
    public Move pickMove() {
        if(Math.random() < 0.5) {
            return new Move(this.magicAttack, 0);
        }
        else {
            return new Move(0, this.physicalAttack);
        }
    }

    /*
     * A method that takes in a move and the player. The method also changes the player's stats based on the
     * damage done by the move.
     * It is separate from resolving a move made by the player to establish a structure that allows later
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
        } return damage;

    }
}
