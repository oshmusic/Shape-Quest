package com.example.team_1e;

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
public class CharacterCustomizationTest extends TestFramework {

    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests that checks that the "Balanced" set of stats are applied to the Player and
     * that the correct stats are displayed when the "Balanced Focus" character option is
     * selected.
     */
    @Test
    public void testCharacterCustomizationBalanced(){
        // skips tutorial, selects balanced focus character, and continues from first story page
        selectBalancedCharacter();

        //checks that the correct player stats are displayed
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 35/35\nMagic Attack: 5\nMagic Defense: 5\nPhysical Attack: 5\nPhysical Defense: 5")));
    }

    /**
     * Tests that checks that the "Magic" set of stats are applied to the Player and
     * that the correct stats are displayed when the "Magic Focus" character option is
     * selected.
     */
    @Test
    public void testCharacterCustomizationMagic(){
        //skips tutorial, selects magic character, and continues from first story page
        selectMagicCharacter();

        //checks that the correct player stats are displayed
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 35/35\nMagic Attack: 6\nMagic Defense: 6\nPhysical Attack: 4\nPhysical Defense: 4")));
    }

    /**
     * Tests that checks that the "Physical" set of stats are applied to the Player and
     * that the correct stats are displayed when the "Physical Focus" character option is
     * selected.
     */
    @Test
    public void testCharacterCustomizationPhysical(){
        //skips tutorial, selects physical character, and continues from first story page
        selectPhysCharacter();

        //checks that the correct player stats are displayed
        Espresso.onView(ViewMatchers.withId(R.id.playerStats)).check(ViewAssertions.
                matches(ViewMatchers.withText("Health: 35/35\nMagic Attack: 4\nMagic Defense: 4\nPhysical Attack: 6\nPhysical Defense: 6")));
    }

}
