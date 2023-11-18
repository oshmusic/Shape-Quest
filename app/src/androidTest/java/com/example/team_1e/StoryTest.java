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
public class StoryTest extends TestFramework {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Tests that the continue button on the story screen successfully displays the next fight.
     */
    @Test
    public void testStoryContinueScreen() {
        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //skips tutorial
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //selects balanced focus character
        Espresso.onView(ViewMatchers.withId(R.id.defaultButton)).perform(ViewActions.click());

        //checks the story screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).check(matches(isDisplayed()));

        //clicks continue button to move to fight screen
        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //checks the continue button worked
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).check(matches(isDisplayed()));
    }

    /**
     * Tests that the correct story text is displayed
     */
    @Test
    public void testStoryDisplay() {
        activityRule.getScenario().onActivity(activity -> {
            activity.testMode = 2;
        });

        //starts game
        Espresso.onView(ViewMatchers.withId(R.id.startNewGameButton)).perform(ViewActions.click());

        //skips tutorial
        Espresso.onView(ViewMatchers.withId(R.id.skipTutorialButton)).perform(ViewActions.click());

        //selects magic focus character
        Espresso.onView(ViewMatchers.withId(R.id.magButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("You are traveling down a long road, carrying a rare medicine for your sister in your pocket. The vial gleams in your bag. A large monster jumps out and steals it. You chase after it and find what seems to be its den. You see a smaller monster guarding the entrance. There's only one way past it.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //first fight
        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        selectMagicAttackAndUpgrade();

        //checks the correct story text is displayed
        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("You enter the structure. A monster looks at you in shock.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //second fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("As you go deeper into the den, you encounter a fork in the road. A monster guards each path. You choose to fight the weaker looking one.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //third fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("The path is strangely narrow. There's nowhere to hide when something appears.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //fourth fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("You come to a room at the end of the road you chose. A slightly bigger monster sits in the corner. You can see it isn't the same one you encountered earlier, so you try to leave without disturbing it. The ground crunches below your feet. The monster turns around and attacks.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //fifth fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("Having hit a dead end, you turn around and try the other path. The guard is still there, but you now feel powerful enough to take it on.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //sixth fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("This path leads to a much larger area than the first. Three monsters are in this room. They are far enough apart that you can take them on one at a time. You approach the one closest to you.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //seventh fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("The other two still haven't noticed you. You again attack the closest one.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //eighth fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("The third finally sees you. It attacks, angry that you have killed its friends.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //ninth fight
        selectMagicAttackAndUpgrade();

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("With the room now cleared, you see a small opening, barely big enough for you to crawl through. With no where else to go, you enter it. It seems to go on forever. The den is much larger than you could have guessed. After what feels like an eternity, the crawl space opens to a room full of shiny objects. Sitting in the center is the monster who stole your vial.")));

        Espresso.onView(ViewMatchers.withId(R.id.storyContinueButton)).perform(ViewActions.click());

        //tenth fight
        //performs magic attack
        Espresso.onView(ViewMatchers.withId(R.id.magicAttackButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.story)).check(ViewAssertions.
                matches(ViewMatchers.withText("You feel stronger than before. You finally have your vial back. The medicine is still intact. Your sister will be saved.")));
    }
}
