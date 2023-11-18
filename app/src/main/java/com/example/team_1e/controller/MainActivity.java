package com.example.team_1e.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team_1e.model.Boss;
import com.example.team_1e.model.EasyBalEnemy;
import com.example.team_1e.model.EasyMagicEnemy;
import com.example.team_1e.model.EasyPhysEnemy;
import com.example.team_1e.model.Enemy;
import com.example.team_1e.model.GameState;
import com.example.team_1e.model.HardBalEnemy;
import com.example.team_1e.model.HardMagicEnemy;
import com.example.team_1e.model.HardPhysEnemy;
import com.example.team_1e.model.MiniBoss;
import com.example.team_1e.model.Move;
import com.example.team_1e.model.Player;
import com.example.team_1e.persistence.IPersistenceFacade;
import com.example.team_1e.persistence.LocalStorageFacade;
import com.example.team_1e.view.CharacterCustomizationFragment;
import com.example.team_1e.view.FightFragment;
import com.example.team_1e.view.GameOverFragment;
import com.example.team_1e.view.ICharacterCustomizationView;
import com.example.team_1e.view.IFightView;
import com.example.team_1e.view.IGameOverView;
import com.example.team_1e.view.IMainMenuView;
import com.example.team_1e.view.IMainView;
import com.example.team_1e.view.ISeeTutorialView;
import com.example.team_1e.view.IStoryView;
import com.example.team_1e.view.ITutorialView;
import com.example.team_1e.view.IUpgradeView;
import com.example.team_1e.view.IVictoryView;
import com.example.team_1e.view.MainMenuFragment;
import com.example.team_1e.view.MainView;
import com.example.team_1e.view.StoryFragment;
import com.example.team_1e.view.Team1eFragmentFactory;
import com.example.team_1e.view.TutorialFragment;
import com.example.team_1e.view.UpgradeFragment;
import com.example.team_1e.view.SeeTutorialFragment;
import com.example.team_1e.view.VictoryFragment;

/**
 * The controller class for the application.
 */

public class MainActivity extends AppCompatActivity
                          implements IFightView.Listener,
                                     IMainMenuView.Listener,
                                     ICharacterCustomizationView.Listener,
                                     ITutorialView.Listener,
                                     IStoryView.Listener,
                                     IUpgradeView.Listener,
                                     ISeeTutorialView.Listener,
                                     IVictoryView.Listener,
                                     IGameOverView.Listener {
    private static final String IN_PROGRESS = "inProgress";
    private static final String PLAYER = "player";
    private static final String ENEMY = "enemy";
    private static final String PROGRESS = "progress";
    private static final String STORY_PROGRESS = "story progress";
    private static final String TUTORIAL_PROGRESS = "tutorial progress";
    private static final String CURRENT_FRAGMENT = "CURRENT FRAGMENT";
    private static final String GAME_STATE = "GAME STATE";
    /**
     * Allows Espresso tests to avoid issues with randomness. Uses an integer to allow for multiple
     * test cases. testMode = 0 is never used. testMode = 1 has setEnemy() always choose an
     * EasyPhysEnemy and firstMoveDecider() always choose "enemy". testMode = 2 has setEnemy()
     * always choose an EasyPhysEnemy, firstMoveDecider() always choose "player", and
     * onMagicUpgrade() always apply a magic attack upgrade, regardless of what the
     * UpgradeFragment chooses.
     */
    public int testMode = 0;
    /**
     * Reference to the main screen template
     */
    private IMainView mainView;
    //private IFightView fightView;

    /**
     * Reference to the upgrade screen
     */
    private UpgradeFragment upgradeView;

    /**
     * Object to store game progress in between uses of the app
     */

    private IPersistenceFacade persistenceFacade;

    /**
     * Object representing the user's character
     */
    private Player p;

    /**
     * Object representing the enemy that the Player is currently facing
     */
    private Enemy e;

    /**
     * Object that keeps track of several values representing the current game state
     */
    private GameState gameState = new GameState();

    /**
     * Value that abilities are incremented by when upgrading
     */
    private int upgradeValue = 3;

    /**
     * Called by Android whenever the activity is created.
     * @param savedInstanceState Saved data from prior implementation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initializes fragment factory to properly (re)load fragments
        getSupportFragmentManager().setFragmentFactory(new Team1eFragmentFactory(this));
        //superclass method call
        super.onCreate(savedInstanceState);


        //sets persistence facade
        this.persistenceFacade = new LocalStorageFacade(this.getFilesDir());

        //retrieves Player object from persistence facade
        this.p = this.persistenceFacade.retrievePlayer();
        if(this.p == null){this.p = new Player();} //might not be needed, but shouldn't be run

        //retrieves Enemy object from persistence facade
        this.e = this.persistenceFacade.retrieveEnemy();
        if(this.e == null){this.e = new Enemy();}

        //retrieves GameState object from persistence facade
        this.gameState = this.persistenceFacade.retrieveGameState();
        if(this.gameState == null){this.gameState = new GameState();}

        //sets up the main view of the app
        this.mainView = new MainView(this);

        this.setContentView(this.mainView.getRootView());

        this.mainView.setProgressTextInvisible();

        //dynamic state preservation
        if(savedInstanceState == null) { //first time activity has loaded
            //display main menu screen
            this.mainView.displayFragment(new MainMenuFragment(this), true, "menu");
        }
        else{ //activity has been loaded before
            //restores previous activity state
            gameState.setProgress(savedInstanceState.getInt(PROGRESS));
            //updates the progress text display
            this.mainView.updateProgressText(gameState.getProgress());
            //restores current progress through the story
            gameState.setStoryProgress(savedInstanceState.getInt(STORY_PROGRESS));
            //restores current progress through the tutorial
            gameState.setTutorialProgress(savedInstanceState.getInt(TUTORIAL_PROGRESS));
            //restores current fragment displayed
            gameState.setCurFrag(savedInstanceState.getString(CURRENT_FRAGMENT));

            //restore Player object state
            this.p = (Player) savedInstanceState.getSerializable(PLAYER);
            assert this.p != null;
            //if current Enemy is not set to null, restore it
            if(savedInstanceState.getSerializable(ENEMY) != null){
                this.e = (Enemy) savedInstanceState.getSerializable(ENEMY);
            }
        }
    }

    /**
     * Saves state information prior to activity destruction.
     * @param outState A bundle where state information is stored.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        // call to method from superclass
        super.onSaveInstanceState(outState);
        //Saves game information to a bundle:
        // A boolean tracking whether or not a game is in progress
        outState.putBoolean(IN_PROGRESS, true);
        // The current Player object
        outState.putSerializable(PLAYER, this.p);
        // The current Enemy object
        outState.putSerializable(ENEMY, this.e);
        // The current GameState object
        outState.putSerializable(GAME_STATE, this.gameState);

        //Saves game information to local files:
        // The current Player object
        this.persistenceFacade.savePlayer(this.p);
        // The current Enemy object
        this.persistenceFacade.saveEnemy(this.e);
        // The current GameState object
        this.persistenceFacade.saveGameState(this.gameState);
    }

    /**
     * A helper method that resets all relevant game stats and objects in preparation for starting
     * a new game.
     */
    public void resetForNewGame(){
        this.p = new Player();
        this.e = new Enemy(0,0,0,0,0,0, 0);
        gameState.setStoryProgress(0);
        gameState.setTutorialProgress(0);
        gameState.setProgress(0);
    }

    /**
     * A method that gets the current GameState for other classes.
     * @return the current GameState
     */
    @Override
    public GameState getGameState() { return this.gameState; }

    //MAIN MENU LISTENER CODE

    /**
     * Called when user wants to start a new game. Resets starting values and displays
     * Character Customization Fragment.
     */
    @Override
    public void onNewGameStart() {
        //resets game state
        this.resetForNewGame();
        //switches fragment display to character customization screen
        this.mainView.displayFragment(new SeeTutorialFragment(this), true, "see tutorial");
        //updates current fragment tracker in game state
        gameState.setCurFrag("see tutorial");
    }

    /**
     * Called when user wants to return to an unfinished game.
     */
    @Override
    public void onLoadSavedGame() {
        //try to load saved game, if one exists
        if (gameState.getCurFrag() != null) {
            //Loads the fragment from the previous game state
            switch (gameState.getCurFrag()) {
                case "see tutorial":
                case "tutorial":
                    this.mainView.displayFragment(new SeeTutorialFragment(this), true, "see tutorial");
                    break;
                case "story":
                    this.mainView.displayFragment(new StoryFragment(this), true, "story");
                    this.mainView.setProgressTextVisible();
                    break;
                case "fight":
                    this.mainView.displayFragment(new FightFragment(this), true, "fight");
                    this.mainView.setProgressTextVisible();
                    break;
                case "upgrade":
                    this.mainView.displayFragment(new UpgradeFragment(this), true, "upgrade");
                    this.mainView.setProgressTextVisible();
                    break;
                case "character customization":
                    this.mainView.displayFragment(new CharacterCustomizationFragment(this), true, "character customization");
                    this.mainView.setProgressTextVisible();
                    break;
                default:
                    this.mainView.displayMessage("No saved game found");
                    break;
            }
            //Updates the progress tracker at the top of the screen
            this.mainView.updateProgressText(gameState.getProgress());
        }
        else {
            this.mainView.displayMessage("No saved game found");
        }
    }

    //CHARACTER CUSTOMIZATION SCREEN LISTENER CODE

    /**
     * Called when user selects the balanced focus stats. Creates a new player using the default
     * Player constructor. Displays See Tutorial Fragment.
     */
    @Override
    public void onBalancedSelection() {
        this.p = new Player();
        this.mainView.displayFragment(new StoryFragment(this), true, "character customization");
        gameState.setCurFrag("story");
    }

    /**
     * Called when user selects the magic focus stats. Creates a new player using the
     * Player constructor. Displays See Tutorial Fragment.
     */
    @Override
    public void onMagicSelection() {
        this.p = new Player(35, 35, 6, 6, 4, 4, 2);
        this.mainView.displayFragment(new StoryFragment(this), true, "character customization");
        gameState.setCurFrag("story");
    }

    /**
     * Called when user selects the physical focus stats. Creates a new player using the
     * Player constructor. Displays See Tutorial Fragment.
     */
    @Override
    public void onPhysicalSelection() {
        this.p = new Player(35, 35, 4, 4, 6, 6, 3);
        this.mainView.displayFragment(new StoryFragment(this), true, "character customization");
        gameState.setCurFrag("story");
    }

    // see tutorial screen listener code

    /**
     * Called when the user wants to see tutorial information about stats. Displays Tutorial Fragment.
     */
    @Override
    public void onStatTutorialButton(){
        gameState.setTutorialProgress(0);
        this.mainView.displayFragment(new TutorialFragment(this), true, "tutorial");
    }

    /**
     * Called when the user wants to see tutorial information about combat. Displays Tutorial Fragment.
     */
    @Override
    public void onFightTutorialButton(){
        gameState.setTutorialProgress(1);
        this.mainView.displayFragment(new TutorialFragment(this), true, "tutorial");
    }

    /**
     * Called when the user wants to see tutorial information about upgrading. Displays Tutorial Fragment.
     */
    @Override
    public void onUpgradeTutorialButton(){
        gameState.setTutorialProgress(2);
        this.mainView.displayFragment(new TutorialFragment(this), true, "tutorial");
    }

    /**
     * Called when the user wants to skip tutorial information or continue after seeing tutorial pages.
     * Displays Tutorial Fragment.
     */
    @Override
    public void onSkipTutorial(){
        this.mainView.displayFragment(new CharacterCustomizationFragment(this), true, "character customization");
        gameState.setCurFrag("character customization");
        this.mainView.setProgressTextVisible();
        this.mainView.updateProgressText(gameState.getProgress());
    }

    //tutorial screen listener code

    /**
     * Called when the user wants to continue after seeing a tutorial page. Displays See Tutorial Fragment.
     */
    @Override
    public void onTutorialContinue() {
       this.mainView.displayFragment(new SeeTutorialFragment(this), true, "see tutorial");
    }

    // story listener code

    /**
     * Called when the user wants to continue after seeing a tutorial page. Updates storyProgress,
     * progress, and game progress text. Displays next Fight Fragment unless user has reached the
     * end of the story, in which case it displays the Victory Fragment.
     */
    @Override
    public void onContinueStory() {
        if (gameState.getStoryProgress() < 10) {
            FightFragment frag = new FightFragment(this);
            this.mainView.displayFragment(frag, true, "fight");
            gameState.setCurFrag("fight");
            this.firstMoveDecider();
            if (gameState.getFirstMover().equals("player")) {
                this.mainView.displayMessage("You will move first!");
            } else if (gameState.getFirstMover().equals("enemy")) {
                this.mainView.displayMessage("The enemy will move first!");
            }
        }
        else {
            this.mainView.displayFragment(new VictoryFragment(this), true, "victory");
            gameState.setCurFrag("victory");
        }
        gameState.addOneStoryProgress();
    }

    //Fight screen listener code

    /**
     * Passes information from the controller to an IFightView object
     * to update the player stats display.
     * @param view The IFightView where the stats display is located
     */
    @Override
    public void updatePlayerStatsDisplay(IFightView view){
        view.updatePlayerStatsDisplay(this.p);
    }

    /**
     * Passes information from the controller to an IFightView object
     * to update the enemy stats display.
     * @param view The IFightView where the stats display is located
     */
    @Override
    public void updateEnemyStatsDisplay(IFightView view){
        view.updateEnemyStatsDisplay(this.e);
    }

    /**
     * Called when the user selects a magic attack. Resolves the following round of combat.
     */
    @Override
    public void onMagicAttack(){
        Move m = this.p.pickMove("MAGIC ATTACK"); //generates the player move
        this.fightHelper(m);
    }

    /**
     * Called when the user selects a physical attack. Resolves the following round of combat.
     */
    @Override
    public void onPhysicalAttack(){
        Move m = this.p.pickMove("PHYSICAL ATTACK"); //generates the player move
        this.fightHelper(m);
    }

    /**
     * Helper method for onPhysicalAttack() and onMagicAttack()
     * Resolves a round of combat where the Player moves first.
     * @param m The move selected by the user.
     */
    private void playerFirstFightHelper(Move m){
        this.p.resolveMove(m, this.e); //applies the player move to the enemy
        if(this.e.getCurrentHealth() <= 0 && gameState.getStoryProgress() < 10){ //if enemy is dead and player has won
            this.upgradeView = new UpgradeFragment(this);
            this.mainView.displayFragment(upgradeView, true, "upgrade"); //go to upgrade screen
            gameState.addOneProgress();
            this.mainView.updateProgressText(gameState.getProgress());
            gameState.setCurFrag("upgrade");
        } else if (this.e.getCurrentHealth() <= 0) {
            this.mainView.displayFragment(new StoryFragment(this), true, "story");
            gameState.addOneProgress();
            this.mainView.updateProgressText(gameState.getProgress());
            gameState.setCurFrag("story");
        }
        if (this.e.getCurrentHealth() > 0) {
            this.e.resolveMove(this.e.pickMove(), this.p); //enemy move
        }
        if (this.p.getCurrentHealth() <= 0){ //if the player is dead and has lost
            //game over screen
            this.mainView.displayFragment(new GameOverFragment(this), true, "game over");
            gameState.setCurFrag("game over");
        }
    }

    /**
     * Helper method for onPhysicalAttack() and onMagicAttack()
     * Resolves a round of combat where the enemy moves first
     * @param m The move selected by the user
     */
    private void enemyFirstFightHelper(Move m) {
        this.e.resolveMove(this.e.pickMove(), this.p); //applies the player move to the enemy
        if (p.getCurrentHealth() <= 0) {
            this.mainView.displayFragment(new GameOverFragment(this), true, "game over");
        }
        if (p.getCurrentHealth() > 0) {
            this.p.resolveMove(m, this.e);
        }
        if(this.e.getCurrentHealth() <= 0 && gameState.getStoryProgress() < 10){ //if enemy is dead and player has won
            this.upgradeView = new UpgradeFragment(this);
            this.mainView.displayFragment(upgradeView, true, "upgrade"); //go to upgrade screen
            gameState.addOneProgress();
            this.mainView.updateProgressText(gameState.getProgress());
            gameState.setCurFrag("upgrade");
        } else if (this.e.getCurrentHealth() <= 0) {
            this.mainView.displayFragment(new StoryFragment(this), true, "story");
            gameState.addOneProgress();
            this.mainView.updateProgressText(gameState.getProgress());
            gameState.setCurFrag("story");
        }
    }

    /**
     * Helper method to decide if the Enemy or Player moves first.
     */
    private void firstMoveDecider() {
        if (testMode == 1) {
            gameState.setFirstMover("enemy");
        } else if (testMode == 2) {
            gameState.setFirstMover("player");
        }
        else if (gameState.getProgress() == 0 || gameState.getProgress() == 6 || gameState.getProgress() == 7) {
            gameState.setFirstMover("player");
        } else if (gameState.getProgress() == 4 || gameState.getProgress() == 8 || gameState.getProgress() == 10) {
            gameState.setFirstMover("enemy");
        } else {
            double random = Math.random();
            if (random < 0.5) {
                gameState.setFirstMover("player");
            } else {
                gameState.setFirstMover("enemy");
            }
        }
    }

    /**
     * Helper method for onPhysicalAttack() and onMagicAttack()
     * Uses the first mover to determine how to resolve the round of combat
     * @param m The move the user selects
     */
    private void fightHelper(Move m) {
        if(gameState.getFirstMover().equals("player")) {
            playerFirstFightHelper(m);
        } else if (gameState.getFirstMover().equals("enemy")) {
            enemyFirstFightHelper(m);
        }
    }

    /**
     * Resets the Enemy object associated with the controller.
     */
    @Override
    public void setEnemy() {
        double randomValue = Math.random();
        if (this.e.getCurrentHealth() <= 0) {
            if (this.testMode != 0) {
                this.e = new EasyPhysEnemy();
            } else if (gameState.getStoryProgress() < 4) {
                if (randomValue < 0.33) {
                    this.e = new EasyBalEnemy();
                } else if (randomValue > 0.64) {
                    this.e = new EasyPhysEnemy();
                } else {
                    this.e = new EasyMagicEnemy();
                }
            } else if (gameState.getStoryProgress() == 4) {
                this.e = new MiniBoss();
            } else if (gameState.getStoryProgress() < 9) {
                if (randomValue < 0.33) {
                    this.e = new HardBalEnemy();
                } else if (randomValue > 0.64) {
                    this.e = new HardPhysEnemy();
                } else {
                    this.e = new HardMagicEnemy();
                }
            } else {
                this.e = new Boss();
            }
        }
    }

    /**
     * Passes information from the controller to an IFightView object to update the images
     * on the IFightView.
     * @param view The IFightView where the images are located.
     */
    @Override
    public void updateImages(IFightView view) {
        view.updateImages(this.p, this.e);
    }

    // upgrade screen listener code

    /**
     * Called when the user selects a physical upgrade. Applies upgrade and displays next
     * Story Fragment.
     */
    @Override
    public void onPhysicalUpgrade() {
        if (this.upgradeView.getPhysUpgrade() < 0.5) {
            this.p.upgradePhysicalAttack(upgradeValue);
            this.mainView.displayMessage("Your Physical Attack is now " + p.getPhysicalAttack());
        } else {
            this.p.upgradePhysicalDefense(upgradeValue);
            this.mainView.displayMessage("Your Physical Defense is now " + p.getPhysicalDefense());
        }
        this.mainView.displayFragment(new StoryFragment(this), true, "story");
        gameState.setCurFrag("story");
    }

    /**
     * Called when the user selects a magic upgrade. Applies upgrade and displays next
     * Story Fragment.
     */
    @Override
    public void onMagicUpgrade() {
        if (this.testMode == 2) {
            this.p.upgradeMagicAttack(upgradeValue);
        }
        else if (this.upgradeView.getMagUpgrade() < 0.5) {
            this.p.upgradeMagicAttack(upgradeValue);
            this.mainView.displayMessage("Your Magic Attack is now " + p.getMagicAttack());
        } else {
            this.p.upgradeMagicDefense(upgradeValue);
            this.mainView.displayMessage("Your Magic Defense is now " + p.getMagicDefense());
        } this.mainView.displayFragment(new StoryFragment(this), true, "story");
        gameState.setCurFrag("story");
    }

    /**
     * Called when the user selects a health upgrade. Applies upgrade and displays next
     * Story Fragment.
     */
    @Override
    public void onHealthUpgrade() {
        if (this.upgradeView.getHealthUpgrade() < 0.8) {
            this.p.upgradeCurrentHealth(upgradeValue);
            this.mainView.displayMessage("Your Current Health is now " + p.getCurrentHealth());
        } else {
            this.p.upgradeMaxHealth(upgradeValue);
            this.mainView.displayMessage("Your Max Health is now " + p.getMaxHealth());
        } this.mainView.displayFragment(new StoryFragment(this), true, "story");
        gameState.setCurFrag("story");
    }

    /**
     * Called when the user wants to skip upgrading their stats. Displays next Story Fragment.
     */
    @Override
    public void onUpgradeSkip(){
        this.mainView.displayFragment(new StoryFragment(this), true, "story");
        gameState.setCurFrag("story");
    }

    /**
     * Displays current player stats on Upgrade Fragments.
     */
    @Override
    public void updatePlayerStatsUpgradeDisplay(IUpgradeView view) {
        view.updatePlayerStatsUpgradeDisplay(this.p);
    }

    //Victory and defeat screens listener code

    /**
     * Called when the user wants to return to the application's main menu. Resets game values and
     * sets screen to display the main menu.
     */
    @Override
    public void onMainMenuReturn() {
        this.mainView.displayFragment(new MainMenuFragment(this), true, "main menu");
        resetForNewGame();
        this.mainView.setProgressTextInvisible();
        gameState.setCurFrag("main menu");
    }
}