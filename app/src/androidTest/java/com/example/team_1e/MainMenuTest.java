package com.example.team_1e;

import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team_1e.controller.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainMenuTest {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test that checks that the "Start New Game" button on the Main Menu screen works as desired
     */
    @Test
    public void testNewGameStart(){
        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //checks that the see tutorial screen appears
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a see
     * tutorial fragment as expected
     */
    @Test
    public void testSeeTutorialLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("see tutorial");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the see tutorial screen appears
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a see
     * tutorial fragment as expected
     * If the user is closes the app while looking at a TutorialFragment, a SeeTutorialFragment
     * should be displayed when they choose to load a saved game.
     */
    @Test
    public void testTutorialLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("tutorial");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the see tutorial screen appears
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a story
     * fragment as expected
     */
    @Test
    public void testStoryLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("story");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the story screen appears
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a fight
     * fragment as expected
     */
    @Test
    public void testFightLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("fight");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the see tutorial screen appears
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads an upgrade
     * fragment as expected
     */
    @Test
    public void testUpgradeLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("upgrade");
            activity.getGameState().setProgress(4);
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the upgrade screen appears
        Espresso.onView(ViewMatchers.withId(R.id.magicUpgradeButton)).check(ViewAssertions.matches(isDisplayed()));

        //checks that the progress bar is correct
        Espresso.onView(ViewMatchers.withId(R.id.progressText)).check(ViewAssertions.
                matches(ViewMatchers.withText("Progress: 4/10")));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a character
     * customization fragment as expected
     */
    @Test
    public void testCharacterCustomizationLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("character customization");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the character customization screen appears
        Espresso.onView(ViewMatchers.withId(R.id.magButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a main
     * menu fragment as expected
     */
    @Test
    public void testMainMenuLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("main menu");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the character customization screen appears
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a main
     * menu fragment as expected
     * If the user closes the app while viewing a VictoryFragment, the load saved game button
     * should display a MainMenuFragment
     */
    @Test
    public void testVictoryLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("victory");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the character customization screen appears
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Test that checks that the "Load Saved Game" button on the Main Menu screen loads a main
     * menu fragment as expected
     * If the user closes the app while viewing a GameOverFragment, the load saved game button
     * should display a MainMenuFragment
     */
    @Test
    public void testGameOverLoadSavedGame() {
        activityRule.getScenario().onActivity(activity -> {
            activity.getGameState().setCurFrag("game over");
        });

        //clicks button to start the game
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).perform(ViewActions.click());

        //checks that the character customization screen appears
        Espresso.onView(ViewMatchers.withId(R.id.loadSavedGameButton)).check(ViewAssertions.matches(isDisplayed()));
    }
}
