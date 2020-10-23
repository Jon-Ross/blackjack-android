package com.ethnlau.blackjack_android;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ethnlau.blackjack_android.game_screen.GameActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GameTest {

    @Rule
    public final ActivityTestRule<GameActivity> mActivityTestRule = new ActivityTestRule<>(GameActivity.class, true, false);

    @Test
    public void useAppContext() {
        mActivityTestRule.launchActivity(new Intent());

        onView(withText("Press \"n\" to start a new blackjack game")).check(matches(isDisplayed()));
    }
}
