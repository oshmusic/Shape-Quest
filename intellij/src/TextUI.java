package src;

import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;
import java.util.ArrayList;
public class TextUI {
    static Scanner scanner = new Scanner(System.in); //A Scanner to take in player input
    static ArrayList<String> upgradeList = new ArrayList<String>(3); //A list to store available upgrade options
    static Game game = new Game(); //An instance of the controller class

    /*
     * A method that takes in player input to determine a move.
     * Input is not case-sensitive but does need to match the phrase.
     * The user can also type in "quit" to exit the program.
     */
    public static String getPlayerMoveInput(){
        String move;
        System.out.println("Your move (physical attack or magic attack): ");

        while(true) {
            move = scanner.nextLine().toUpperCase();
            if (move.compareTo("PHYSICAL ATTACK") == 0 || move.compareTo("MAGIC ATTACK") == 0) {
                return move;
            } else if (move.compareTo("QUIT") == 0) {
                return "quit";
            } else{
                System.out.println("Invalid Move. Type \"physical attack\" or \"magic attack\"");
            }
        }
    }

    /*
     * A method that takes in player input to select an upgrade.
     * The player is presented with three options, each with an associated letter. The player selects an option by entering
     * the corresponding letter. Only the displayed letters, "skip", and "quit" are accepted inputs (not case-sensitive).
     */
    public static String getPlayerUpgradeInput(){
        System.out.println("Select upgrade (\"skip\" to skip): ");
        String input;
        //upgradeList contains 3 options for valid input
        String case1 = upgradeList.get(0);
        String case2 = upgradeList.get(1);
        String case3 = upgradeList.get(2);
        while(true){
            input = scanner.nextLine().toUpperCase();
            //Checks for valid input
            if((input.equals(case1)) || (input.equals(case2)) || (input.equals(case3)) || (input.equals("SKIP"))){
                return input;
            } else if (input.compareTo("QUIT") == 0) {
                return "quit";
            }
            else {
                System.out.println("Sorry, try again.");
            }
        }
    }


    /*
     * This method randomly selects 3 of 6 upgrade options (one from each pair of upgrades),
     * stores the selections in upgradeList so that other methods can access them, and then displays them to the user.
     */
    public static void displayUpgradeOptions(){
        String upgrade1;
        String upgrade2;
        String upgrade3;

        /*
         * For clarification, there are 6 total upgrade options, in 3 pairs. One of each pair will be randomly
         * selected and displayed every time that this method is called. upgradeList keeps track of these
         * selections for use in getPlayerUpgradeInput()
         */

        if(Math.random() < 0.5){
            upgrade1 = "Physical Attack (A)";
            upgradeList.set(0, "A");
        }
        else{
            upgrade1 = "Physical Defense (B)";
            upgradeList.set(0, "B");
        }

        if (Math.random() < 0.5) {
            upgrade2 = "Magic Attack (C)";
            upgradeList.set(1, "C");
        }
        else{
            upgrade2 = "Magic Defense (D)";
            upgradeList.set(1, "D");
        }

        if(Math.random() < 0.5 ){
            upgrade3 = "Current Health (E)";
            upgradeList.set(2, "E");
        }
        else {
            upgrade3 = "Maximum Health (F)";
            upgradeList.set(2, "F");
        }

        System.out.println("UPGRADE OPTIONS:\n" + upgrade1 + "\n" + upgrade2 + "\n" + upgrade3);
    }


    /*
     * This method displays the current game state to the user using the toString() method in the Game class.
     */
    public static void displayStatus(){
        System.out.println(game);
    }

    /*
     * This method displays a story fragment to the user and takes in input (not case-sensitive) to either continue
     * or quit. Continuing progresses the game, while quitting ends it. If neither "continue" or "quit" is entered, the
     * user will be prompted to enter a valid input until one is entered.
     */
    public static String displayStory(String story){
        System.out.println(story);
        System.out.println("Please enter \"continue\" to continue or \"quit\" to quit");
        String input;
        while(true) {
            input = scanner.nextLine().toUpperCase();
            if (input.equals("CONTINUE")) {
                return input;
            } else if (input.equals("QUIT")) {
                return input;
            } else {
                System.out.println("Please enter \"continue\" to continue or \"quit\" to quit");
            }
        }
    }

    public static String displayTutorial(String tutorialPage){
        System.out.println(tutorialPage);
        System.out.println("Please enter \"continue\" to continue or \"quit\" to quit");
        String input;
        while(true) {
            input = scanner.nextLine().toUpperCase();
            if (input.equals("CONTINUE")) {
                return input;
            } else if (input.equals("QUIT")) {
                return input;
            } else {
                System.out.println("Please enter \"continue\" to continue or \"quit\" to quit");
            }
        }

    }

    public static void displayPlayerStats(Player p){
        System.out.printf("Current stats: \nHealth: %d/%d \nMagic Attack: %d \nMagic Defense: %d \nPhysical Attack: %d \nPhysical Defense: %d\n============\n",
                p.currentHealth, p.maxHealth, p.magicAttack, p.magicDefense, p.physicalAttack, p.physicalDefense);
    }

    /*
     * This method takes in a message string and displays it to the user.
     */
    public static void displayMessage(String message){
        System.out.println(message);
    }

    public static String displayCustomizationOptions(){
        System.out.println("CHARACTER CUSTOMIZATION");
        System.out.println("=======================");
        System.out.println("OPTION 1:\n============");
        System.out.println("Health: 20 \nMagic Attack: 2 \nMagic Defense: 2 \nPhysical Attack: 8 \nPhysical Defense: 8 \n============");
        System.out.println("OPTION 2:\n============");
        System.out.println("Health: 20 \nMagic Attack: 8 \nMagic Defense: 8 \nPhysical Attack: 2 \nPhysical Defense: 2 \n============");
        System.out.println("OPTION 3:\n============");
        System.out.println("Health: 20 \nMagic Attack: 5 \nMagic Defense: 5 \nPhysical Attack: 5 \nPhysical Defense: 5 \n============");

        String selection;
        System.out.println("Please choose a character: 1, 2, or 3: ");
        while(true){
            selection = scanner.nextLine();
            if(selection.equals("1") || selection.equals("2") || selection.equals("3") || (selection.equalsIgnoreCase("quit"))) {
                return selection;
            }
            else{
                System.out.println("Invalid selection, please try again: ");
            }
        }
    }

    public static void main(String[]args){
        //initializes upgradeList with placeholders to be overwritten later
        upgradeList.add( "A");
        upgradeList.add("A");
        upgradeList.add("A");
        String again; //stores whether the user wants to play again
        boolean keepGoing = true; //tracks whether the user has entered "quit"
        //The "main menu"
        System.out.println("Welcome!");
        System.out.println("Type \"start\" to launch the game or \"quit\" to quit: ");
        String start; //user input to start or quit the game from the main menu
        while (true){
            start = scanner.nextLine();
            if (start.equalsIgnoreCase("Start")) {
                System.out.println("Game starts...");
                break;
            }
            else if(start.equalsIgnoreCase("Quit")){
                keepGoing = false;
                break;
            }
            else {
                System.out.println("Type \"start\" to launch the game or \"quit\" to quit: ");
            }
        }

        //Character customization
        if(game.customizeCharacter().equalsIgnoreCase("quit")){
            keepGoing = false;
        }
        //The main loop of the game
        while(keepGoing) { //repeats until player exits the game
            //The tutorial
            String tutorial; // user input to view or skip the tutorial
            System.out.println("Do you want to see the tutorial? Y/N");
            while(true) {
                tutorial = scanner.nextLine();
                if(tutorial.equalsIgnoreCase("quit")){
                    keepGoing = false;
                    break;
                }
                else if (tutorial.equalsIgnoreCase("Y")) {
                    game.showTutorial();
                    System.out.println("Do you want to see the tutorial again? Y/N");
                    if (tutorial.equalsIgnoreCase("Y")) {
                        continue;
                    } else if (tutorial.equalsIgnoreCase("N")) {
                        break;
                    }
                }
                else if (tutorial.equalsIgnoreCase("N")){
                    break;
                }
                else {
                    System.out.println("Sorry, please try again.");
                }
            }
            //runs rounds of the game until the story completes
            for (int i = 0; i < game.storyCounter; i++) {
                if (game.round(game.p).compareTo("quit") == 0) { //if the user wants to quit
                    keepGoing = false;
                    break;
                }
            }
            //if the user has reached the end of the story (completed all rounds)
            if (keepGoing) {System.out.println("Congratulations! You reached the end!");}
            while(keepGoing) { //repeats until player picks y or n
                System.out.println("Play again? y/n");
                again = scanner.nextLine();
                if(again.equalsIgnoreCase("y")){
                    System.out.println("Your next adventure begins...");
                    game.beginGame();
                    break;
                }
                else if (again.equalsIgnoreCase("n")) {
                    keepGoing = false;
                    break;
                }
                else{
                    System.out.println("Please select y or n");
                }
            }
        }
    }
}
