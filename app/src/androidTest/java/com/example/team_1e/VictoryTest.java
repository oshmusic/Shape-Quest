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
public class VictoryTest extends TestFramework {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests to make sure the victory screen is displayed when the user wins and the
     * main manu button returns to the main menu.
     */
    @Test
    public void testVictory() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 2;
        });
        //due to testMode, the text on the magicUpgradeButton will not always match the applied upgrade

        selectMagicCharacter();

        //first fight
        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //performs magic attack and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //second fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //third fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //fourth fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //fifth fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //sixth fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //seventh fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //eighth fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //ninth fight and upgrade and continues story
        selectMagicAttackAndUpgradeAndStory();

        //tenth fight
        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //continues after seeing story
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks the victory screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.victoryMessageText)).check(ViewAssertions.
                matches(ViewMatchers.withText("VICTORY!")));

        Espresso.onView(ViewMatchers.withId(R.id.victoryMainMenuButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).check(matches(isDisplayed()));
    }
}
