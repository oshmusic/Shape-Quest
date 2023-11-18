package com.example.team_1e;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
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
public class GameOverTest extends TestFramework {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);



    /**
     * Tests to make sure the game over screen is displayed when the user loses and the
     * main manu button returns to the main menu.
     */
    @Test
    public void testGameOver() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        Espresso.onView(ViewMatchers.withId(R.id.gameOverText)).check(ViewAssertions.
                        matches(ViewMatchers.withText("GAME OVER")));

        Espresso.onView(ViewMatchers.withId(R.id.mainMenuButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).check(matches(isDisplayed()));
    }
}
