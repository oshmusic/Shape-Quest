package com.example.team_1e.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class APlayerTest {


    /**
     * Tests resolveMove() by creating Moves, Players, and Enemies and checking results.
     */
    @Test
    void testResolveMove() {
        Player p1 = new Player();
        Player p2 = new Player(20,20,4,15,4,15,0);
        Move m1 = new Move(5,0);
        Move m2 = new Move(0,5);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(20,20,3,20,3,20,0);
        Move m3 = new Move(5,0);
        Move m4 = new Move(0,5);

        assertEquals(5 + 5 - p1.magicDefense, e1.resolveMove(m1,p1));
        assertEquals(1, e1.resolveMove(m1,p2));
        assertEquals(5 + 5 - p1.physicalDefense, e1.resolveMove(m2,p1));
        assertEquals(1, e1.resolveMove(m2,p2));

        assertEquals(5 + 5 - e1.magicDefense, p1.resolveMove(m3,e1));
        assertEquals(5 + 5 - e1.magicDefense, p1.resolveMove(m4,e1));
        assertEquals(1,p1.resolveMove(m1,e2));
        assertEquals(1,p1.resolveMove(m2,e2));
    }
    /**
     * Tests toString() method by creating Players and Enemies and checking result.
     */
    @Test
    void testToString() {
        Player p1 = new Player(5, 5, 5, 5, 5, 5, 0);
        Player p2 = new Player();
        Enemy e1 = new Enemy(10, 20, 7, 7, 3, 3, 0);
        Enemy e2 = new Enemy();

        assertEquals("Health: " + p1.currentHealth + "/" + p1.maxHealth + "\nMagic Attack: " + p1.magicAttack +
                "\nMagic Defense: " + p1.magicDefense + "\nPhysical Attack: " + p1.physicalAttack + "\nPhysical Defense: " +
                p1.physicalDefense, p1.toString());
        assertEquals("Health: " + p2.currentHealth + "/" + p2.maxHealth + "\nMagic Attack: " + p2.magicAttack +
                "\nMagic Defense: " + p2.magicDefense + "\nPhysical Attack: " + p2.physicalAttack + "\nPhysical Defense: " +
                p2.physicalDefense, p2.toString());
        assertEquals("Health: " + e1.currentHealth + "/" + e1.maxHealth + "\nMagic Attack: " + e1.magicAttack +
                "\nMagic Defense: " + e1.magicDefense + "\nPhysical Attack: " + e1.physicalAttack + "\nPhysical Defense: " +
                e1.physicalDefense, e1.toString());
        assertEquals("Health: " + e2.currentHealth + "/" + e2.maxHealth + "\nMagic Attack: " + e2.magicAttack +
                "\nMagic Defense: " + e2.magicDefense + "\nPhysical Attack: " + e2.physicalAttack + "\nPhysical Defense: " +
                e2.physicalDefense, e2.toString());

    }

    /**
     * Tests getCurrentHealth() by creating Players and Enemies and checking result.
     */
    @Test
    void testGetCurrentHealth() {
        Player p1 = new Player();
        Player p2 = new Player(10, 20, 3, 3, 4, 4, 0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(12, 15, 4, 4, 3, 3, 0);

        assertEquals(p1.currentHealth, p1.getCurrentHealth());
        assertEquals(p2.currentHealth, p2.getCurrentHealth());
        assertEquals(e1.currentHealth, e1.getCurrentHealth());
        assertEquals(e2.currentHealth, e2.getCurrentHealth());
    }

    /**
     * Tests upgradePhysicalAttack() by creating Players and Enemies, adding to their physical attack, and checking result.
     */
    @Test
    void testUpgradePhysicalAttack() {
        Player p1 = new Player();
        Player p2 = new Player(12, 20, 4, 6, 3, 7, 0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(3, 12, 6, 7, 8, 9, 0);

        assertEquals(p1.physicalAttack + 4, p1.upgradePhysicalAttack(4));
        assertEquals(p2.physicalAttack + 5, p2.upgradePhysicalAttack(5));
        assertEquals(e1.physicalAttack, e1.upgradePhysicalAttack(0));
        assertEquals(e2.physicalAttack + 7, e2.upgradePhysicalAttack(7));
    }

    /**
     * Tests upgradePhysicalDefense() by creating Players and Enemies, adding to their physical defense, and checking result.
     */
    @Test
    void testUpgradePhysicalDefense() {
        Player p1 = new Player();
        Player p2 = new Player(3, 25, 4, 3, 6, 8, 0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(7, 10, 4, 5, 8, 9, 0);

        assertEquals(p1.physicalDefense, p1.upgradePhysicalDefense(0));
        assertEquals(p2.physicalDefense + 5, p2.upgradePhysicalDefense(5));
        assertEquals(e1.physicalDefense + 7, e1.upgradePhysicalDefense(7));
        assertEquals(e2.physicalDefense + 6, e2.upgradePhysicalDefense(6));
    }

    /**
     * Tests upgradeMagicAttack() by creating Players and Enemies, adding to their magic attack, and checking result.
     */
    @Test
    void testUpgradeMagicAttack() {
        Player p1 = new Player();
        Player p2 = new Player(7, 7, 7, 7, 7, 7, 0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(12, 12, 12, 12, 12, 12, 0);

        assertEquals(p1.magicAttack + 3, p1.upgradeMagicAttack(3));
        assertEquals(p2.magicAttack, p2.upgradeMagicAttack(0));
        assertEquals(e1.magicAttack + 7, e1.upgradeMagicAttack(7));
        assertEquals(e2.magicAttack + 10, e2.upgradeMagicAttack(10));
    }

    /**
     * Tests upgradeMagicDefense() by creating Players and Enemies, adding to their magic defense, and checking result.
     */
    @Test
    void testUpgradeMagicDefense() {
        Player p1 = new Player();
        Player p2 = new Player(5,6,7,8,9,10,0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(1,9,6,4,3,7,0);

        assertEquals(p1.magicDefense + 1, p1.upgradeMagicDefense(1));
        assertEquals(p2.magicDefense + 4, p2.upgradeMagicDefense(4));
        assertEquals(e1.magicDefense + 11, e1.upgradeMagicDefense(11));
        assertEquals(e2.magicDefense, e2.upgradeMagicDefense(0));
    }

    /**
     * Tests upgradeCurrentHealth() by creating Players and Enemies, adding to their current health, and checking result.
     */
    @Test
    void testUpgradeCurrentHealth() {
        Player p1 = new Player();
        Player p2 = new Player(3,25,4,5,7,8,0);
        Player p3 = new Player(18,20,5,6,7,8,0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(5,30,4,2,3,1,0);
        Enemy e3 = new Enemy(19,20,4,3,6,5,0);

        assertEquals(p1.currentHealth, p1.upgradeCurrentHealth(4));
        assertEquals(p2.currentHealth + 7, p2.upgradeCurrentHealth(7));
        assertEquals(p2.currentHealth, p2.upgradeCurrentHealth(0));
        assertEquals(p3.maxHealth,p3.upgradeCurrentHealth(5));
        assertEquals(e1.currentHealth, e1.upgradeCurrentHealth(6));
        assertEquals(e2.currentHealth + 25, e2.upgradeCurrentHealth(25));
        assertEquals(e2.currentHealth, e2.upgradeCurrentHealth(0));
        assertEquals(e3.maxHealth,e3.upgradeCurrentHealth(5));

    }

    /**
     * Tests upgradeMax() by creating Players and Enemies, adding to their max health, and checking result.
     */
    @Test
    void testUpgradeMaxHealth() {
        Player p1 = new Player();
        Player p2 = new Player(3,3,3,3,3,3,0);
        Enemy e1 = new Enemy();
        Enemy e2 = new Enemy(9,5,6,4,5,1,0);

        assertEquals(p1.maxHealth + 6, p1.upgradeMaxHealth(6));
        assertEquals(p2.maxHealth, p2.upgradeMaxHealth(0));
        assertEquals(e1.maxHealth + 63, e1.upgradeMaxHealth(63));
        assertEquals(e2.maxHealth + 2, e2.upgradeMaxHealth(2));
    }
}