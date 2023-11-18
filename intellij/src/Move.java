package src;

/*
* An initial draft of the src.Move class, which stores src.Player and src.Enemy decisions during combat.
*/
public class Move {
    private int magicAttack;
    private int physicalAttack;

    /*
    * A constructor for the src.Move class that initializes all fields
     */
    public Move(int magAtk, int physAtk){
        this.magicAttack = magAtk;
        this.physicalAttack = physAtk;
    }

    //Methods to access the field values
    /*
    * Returns the magicAttack value of the src.Move
    * @return the magicAttack value of the src.Move
    */
    public int getMagicAttack(){
        return this.magicAttack;
    }

    /*
     * Returns the physicalAttack value of the src.Move
     * @return the physicalAttack value of the src.Move
     */
    public int getPhysicalAttack(){
        return this.physicalAttack;
    }


}
