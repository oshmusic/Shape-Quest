package com.example.team_1e;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

public class TestFramework {
    /**
     * Method to start the game, skip the tutorial, select the balanced focus character, and
     * continue past the first story screen. Aids readability of tests.
     */
    void selectBalancedCharacter() {
        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //skips tutorial
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //selects balanced focus character
        Espresso.onView(ViewMatchers.withId(R.id.defaultButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());
    }

    /**
     * Method to start the game, skip the tutorial, select the magic focus character, and
     * continue past the first story screen. Aids readability of tests.
     */
    void selectMagicCharacter() {
        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //skips tutorial
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //selects magic focus character
        Espresso.onView(ViewMatchers.withId(R.id.magButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());
    }

    /**
     * Method to start the game, skip the tutorial, select the physical focus character, and
     * continue past the first story screen. Aids readability of tests.
     */
    void selectPhysCharacter() {
        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //skips tutorial
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //selects physical focus character
        Espresso.onView(ViewMatchers.withId(R.id.physButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());
    }

    /**
     * Method to select the magic attack, select the magic upgrade, and continue past a story
     * screen. Aids readability of tests.
     */
    void selectMagicAttackAndUpgradeAndStory() {
        //selects Magic Attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //selects magic upgrade
        Espresso.onView(ViewMatchers.withId(R.id.magicUpgradeButton)).perform(ViewActions.click());

        //clicks story screen continue button to continue to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());
    }

    /**
     * Method to select the magic attack and select the magic upgrade. Aids readability of tests.
     */
    void selectMagicAttackAndUpgrade() {
        //selects Magic Attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        //selects magic upgrade
        Espresso.onView(ViewMatchers.withId(R.id.magicUpgradeButton)).perform(ViewActions.click());
    }
}
