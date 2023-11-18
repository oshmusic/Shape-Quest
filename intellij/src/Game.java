package src;

public class Game {
    // fields

    // The player of the game
    Player p = new Player();

    // An enemy the player fights against
    Enemy e;

    // The total number of story fragments there are, minus 1.
    int storyCounter = 2;

    // The current story fragment the player is meant to see. Updated after the player sees that fragment.
    int currentStoryNumber = 0;

    // The value player stats are upgraded by in the upgradeStats method.
    int upgradeValue = 3;

    //The story for the game
    String[] story = {"Story fragment 1", "Story fragment 2", "Story fragment 3"};

    //Tutorial text; currently placeholders
    String[] tutorial = {"tutorial page 1", "tutorial page 2", "tutorial page 3"};

    // empty constructor for game
    public Game() {
    }

    // methods
   /*
   * A method that updates the player's stats based on the upgrade they chose. It also allows the player
   * to skip upgrading or quit the program.
    */
    public String upgradeStats(Player p) {
        TextUI.displayPlayerStats(p);
        TextUI.displayUpgradeOptions();
        String input = TextUI.getPlayerUpgradeInput();
        if (input.equals("quit")) {
            return "quit";
        } else if (input.equals("SKIP")) {
            return "skip";
        } else if (input.equals("A")) {
            p.physicalAttack += upgradeValue;
            return "physATK";
        } else if (input.equals("B")) {
            p.physicalDefense += upgradeValue;
            return "physDEF";
        } else if (input.equals("C")) {
            p.magicAttack += upgradeValue;
            return "magATK";
        } else if (input.equals("D")) {
            p.magicDefense += upgradeValue;
            return "magDEF";
        } else if (input.equals("E")) {
            p.currentHealth += upgradeValue;
            //avoids current health exceeding max health
            if(p.currentHealth > p.maxHealth){
                p.currentHealth = p.maxHealth;
            }
            return "currHealth";
        } else if (input.equals("F")) {
            p.maxHealth += upgradeValue;
            return "maxHealth";
        } else {return "none";}
    }

    /*
    * This method returns the player and enemy health status. Used to show result of moves to the player.
     */
    public String toString() {
        return "Player health: " + p.currentHealth + "/" + p.maxHealth + ", Enemy health: " +
                e.currentHealth + "/" + e.maxHealth;
    }

   /*
   * This method processes one fight. It allows the player and enemy to pick moves, resolves those moves, and
   * checks if the with is over after each move. It also displays the result of these moves to the player.
   * The player can quit at any time.
    */
    public String fight(Player p, Enemy e) {
        System.out.println("An enemy approaches!");
        while (true) {
            String userText = TextUI.getPlayerMoveInput();
            if (userText.equals("quit")) {
                return "quit";
            }
            Move userMove = p.pickMove(userText);
            p.resolveMove(userMove, e);
            if (e.currentHealth <= 0) {
                TextUI.displayMessage("Victory!");
                return "win";
            }
            TextUI.displayStatus();
            TextUI.displayMessage("The enemy attacks!");
            Move enemyMove = e.pickMove();
            e.resolveMove(enemyMove, p);
            if (p.currentHealth <= 0) {
                TextUI.displayMessage("Defeat");
                TextUI.displayMessage("Returning to the beginning of the game...");
                TextUI.displayMessage("Game starts...");
                return "defeat";
            }
            TextUI.displayStatus();
        }
    }

    /*
    * This method resets the game by creating a new Player with default stats and resetting the story.
     */
    public void beginGame() {
        p = new Player();
        currentStoryNumber = 0;
    }


    /*
    * This method shows the player the next story fragment and updates the current story number.
     */
    public String presentStory(int storyNumber) {
        String currentStory = story[storyNumber];
        String userText = TextUI.displayStory(currentStory);
        currentStoryNumber++;
        return userText;
    }

    /*
     * Draft of a method to present the tutorial pages to the user
     */
    public String showTutorial(){
        for(int i = 0; i < tutorial.length; i++){
            String currentTutorial = tutorial[i];
            String userInput = TextUI.displayTutorial(currentTutorial);
            if (userInput.equalsIgnoreCase("quit")){
                return "quit";
            }
        }
        return "done";
    }

    public String customizeCharacter(){
        String selection = TextUI.displayCustomizationOptions();
        switch (selection){
            case "1":
                this.p = new Player(20, 20, 2, 2, 8, 8);
                break;
            case "2":
                this.p = new Player(20, 20, 8, 8, 2, 2);
                break;
            default:
        }
        return selection;
    }

    /*
    * This method processes a round, which is the main gameplay loop. It shows the first story fragment and
    * runs a fight. If the player loses that fight, it calls beginGame. If the player wins that fight, it
    * calls upgradeStats and presentStory. The player can quit at any time.
     */
    public String round(Player p) {
        if (currentStoryNumber == 0) {
            if (this.presentStory(this.currentStoryNumber).equalsIgnoreCase("quit")) {
                return "quit";
            }
        }
        e = new Enemy();
        String result = this.fight(p, e);
        if (result.equals("quit")) {
            return "quit";
        }
        else if (result.equals("win")) {
            if(this.upgradeStats(p).equals("quit")) {
                return "quit";
            } else if (this.presentStory(this.currentStoryNumber).equalsIgnoreCase("quit")) {
                return "quit";
            }
            return "next";
        }
        else {
            this.beginGame();
            return "restart";
        }
    }
}
