package com.example.team_1e;

import android.os.SystemClock;
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
public class FightTest extends TestFramework {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests that the enemy's attacks successfully change the Player's stats when the enemy attacks
     * first and the change is reflected on the fight screen.
     */
    @Test
    public void testEnemyAttackFirst() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectBalancedCharacter();

        //selects physical attack
        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        //checks that the enemy does damage
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 31/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));

        SystemClock.sleep(2000);

        //selects physical attack
        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        //skips upgrade
        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        //continues from story screen to fight
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the enemy attacked first
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 27/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));

        //selects magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that the enemy does damage
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 23/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));
    }

    /**
     * Tests that the enemy's attacks successfully change the Player's stats when the enemy attacks
     * second and the change is reflected on the fight screen.
     */
    @Test
    public void testEnemyAttackSecond() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 2;
        });

        selectBalancedCharacter();

        //selects physical attack
        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        //checks that the enemy does damage
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 31/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));

        SystemClock.sleep(2000);

        //selects physical attack
        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        SystemClock.sleep(2000);

        //skips upgrade
        Espresso.onView(ViewMatchers.withId(R.id.upgradeSkipButton)).perform(ViewActions.click());

        //continues from story screen to fight
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks that the enemy attacked second
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 31/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));

        //selects magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that the enemy does damage
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 27/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));
    }

    /**
     * Tests that the physical attack button successfully changes the Enemy's stats and the change
     * is reflected on the fight screen.
     */
    @Test
    public void testPhysicalAttack() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //selects physical attack
        Espresso.onView(ViewMatchers.withId(R.id.physicalAttackButton)).perform(ViewActions.click());

        //checks that damage was applied to enemy
        Espresso.onView(ViewMatchers.withId(R.id.enemyStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 5/10\nMagic Attack: 2\nMagic Defense: 2\nPhysical Attack: 4\nPhysical Defense: 4")));
    }

    /**
     * Tests that the magic attack button successfully changes the Enemy's stats and the change
     * is reflected on the fight screen.
     */
    @Test
    public void testMagicAttack() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 1;
        });

        selectMagicCharacter();

        //selects magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks that damage was applied to enemy
        Espresso.onView(ViewMatchers.withId(R.id.enemyStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 1/10\nMagic Attack: 2\nMagic Defense: 2\nPhysical Attack: 4\nPhysical Defense: 4")));
    }

    /**
     * Tests that the progress tracker at the top of the screen updates after the user
     * wins a Fight and displays the update properly.
     */
    @Test
    public void testProgressBar() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 2;
        });

        selectMagicCharacter();

        //checks story progress is correct
        Espresso.onView(ViewMatchers.withId(R.id.progressText)).check(ViewAssertions.
                matches(ViewMatchers.withText("Progress: 0/10")));

        //selects magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //selects magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //checks story progress updated
        Espresso.onView(ViewMatchers.withId(R.id.progressText)).check(ViewAssertions.
                matches(ViewMatchers.withText("Progress: 1/10")));
    }
}
