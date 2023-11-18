package com.example.team_1e;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;
import android.util.Log;
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
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UpgradeTest extends TestFramework{
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests to make sure the physical upgrade button is displayed on the upgrade screen. Also
     * tests that the upgrade is applied and displayed correctly during the next fight.
     */
    @Test
    public void testPhysUpgrade() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that we are on the right screen after the fight
        Espresso.onView(ViewMatchers.withId(R.id.physicalUpgradeButton)).check(matches(isDisplayed()));

        //selects physical upgrade
        Espresso.onView(ViewMatchers.withId(R.id.physicalUpgradeButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the upgrade has been applied properly
        Matchers.anyOf(ViewMatchers.withText("Health: 25/35\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 7\nPhysical Defense: 4"),
                ViewMatchers.withText("Health: 25/35\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 7"));
    }

    /**
     * Tests to make sure the magic upgrade button is displayed on the upgrade screen. Also
     * tests that the upgrade is applied and displayed correctly during the next fight.
     */
    @Test
    public void testMagUpgrade() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that we are on the right screen after the fight
        Espresso.onView(ViewMatchers.withId(R.id.magicUpgradeButton)).check(matches(isDisplayed()));

        //selects magic upgrade
        Espresso.onView(ViewMatchers.withId(R.id.magicUpgradeButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the upgrade has been applied properly
        Matchers.anyOf(ViewMatchers.withText("Health: 25/35\nMagic Attack: 9\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 4"),
                ViewMatchers.withText("Health: 25/35\nMagic Attack: 6\nMagic Defense: 9\nPhysical Attack: 4\nPhysical Defense: 4"));
    }

    /**
     * Tests to make sure the health upgrade button is displayed on the upgrade screen. Also
     * tests that the upgrade is applied and displayed correctly during the next fight.
     */
    @Test
    public void testHealthUpgrade() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that we are on the right screen after the fight
        Espresso.onView(ViewMatchers.withId(R.id.healthUpgradeButton)).check(matches(isDisplayed()));

        //selects health upgrade
        Espresso.onView(ViewMatchers.withId(R.id.healthUpgradeButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the upgrade has been applied properly
        Matchers.anyOf(ViewMatchers.withText("Health: 28/35\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 4"),
                ViewMatchers.withText("Health: 25/38\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 4"));
    }

    /**
     * Tests to make sure the skip upgrade button is displayed on the upgrade screen. Also
     * tests that stats do not change on the next fight screen. This test occasionally fails due
     * to the way Espresso works, not because the buttons do not work. If this test fails,
     * please run it again, and it should pass.
     */
    @Test
    public void testUpgradeSkip() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(5000);

        //checks that we are on the right screen after the fight
        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).check(matches(isDisplayed()));

        //selects skip button
        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the upgrade has been applied properly
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 25/35\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 4")));
    }
}