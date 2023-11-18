package com.example.team_1e;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.team_1e.controller.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TutorialTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests that the SeeTutorialFragment buttons display the correct tutorial text.
     * Also tests that the skip button continues to a CharacterCustomizationFragment.
     * Also tests that the continue button on TutorialFragments works as expected.
     */
    @Test
    public void testTutorial() {
        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //checks that tutorial options are displayed
        Espresso.onView(ViewMatchers.withId(R.id.statTutorialButton)).check(matches(isDisplayed()));

        //clicks the stat tutorial button
        Espresso.onView(ViewMatchers.withId(R.id.statTutorialButton)).perform(ViewActions.click());

        //checks that the stat tutorial is displayed
        Espresso.onView(ViewMatchers.withId(R.id.tutorial)).check(ViewAssertions.
                matches(ViewMatchers.withText("How the game works: All entities have two skills: Magic and Physical. Each skill has an attack and defense stat. The attack stat determines the power of an attack made with that skill, while the defense stat reduces damage taken from attacks made with that skill.")));

        //goes back to the tutorial menu
        Espresso.onView(ViewMatchers.withId(R.id.tutorialContinueButton)).perform(ViewActions.click());

        //checks that the tutorial continue button works
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).check(matches(isDisplayed()));

        //clicks the combat tutorial button
        Espresso.onView(ViewMatchers.withId(R.id.combatTutorialButton)).perform(ViewActions.click());

        //checks that the combat tutorial is displayed
        Espresso.onView(ViewMatchers.withId(R.id.tutorial)).check(ViewAssertions.
                matches(ViewMatchers.withText("In a fight, combat occurs in turns. Sometimes you will move first, but other times your opponent will. All moves are resolved before the next turn starts. To select your move, click on the button labeled with the attack type you want (“Magic Attack” or “Physical Attack”).")));

        //goes back to the tutorial menu
        Espresso.onView(ViewMatchers.withId(R.id.tutorialContinueButton)).perform(ViewActions.click());

        //clicks the upgrade tutorial button
        Espresso.onView(ViewMatchers.withId(R.id.upgradeTutorialButton)).perform(ViewActions.click());

        //checks that the upgrade tutorial is displayed
        Espresso.onView(ViewMatchers.withId(R.id.tutorial)).check(ViewAssertions.
                matches(ViewMatchers.withText("After completing each fight, you will be presented with three upgrade options. One is always related to your Magic skill, one is always related to your Physical skill, and one is always related to your health.")));

        //goes back to the tutorial menu
        Espresso.onView(ViewMatchers.withId(R.id.tutorialContinueButton)).perform(ViewActions.click());

        //clicks the skip tutorial button
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //checks that the skip button worked
        Espresso.onView(ViewMatchers.withId(R.id.defaultButton)).check(matches(isDisplayed()));
    }
}
