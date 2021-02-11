package com.ethnlau.blackjack_android;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ethnlau.blackjack_android.game_screen.DealerProvider;
import com.ethnlau.blackjack_android.game_screen.GameActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class GameTest {

    @Rule
    public final ActivityTestRule<GameActivity> mActivityTestRule = new ActivityTestRule<>(GameActivity.class, true, false);

    private GamePage page;

    @Before
    public void setUp() throws Exception {
        page = new GamePage();
    }

    @Test
    public void GivenPlayerHandBetterThanHouse_WhenRunGame_ThenPlayerWinsAndCanPlayAgain() {
        page.setUpDealerMock(10, 2, 7, 5, 7, 5, 5, 10);

        mActivityTestRule.launchActivity(new Intent());

        page.checkStartingGameState();
        page.tapPlayButton();
        page.checkPlayerHand(10, 2);
        page.checkHouseHand();
        page.tapTwistButton();
        page.checkPlayerHand(10, 2, 7);
        page.tapStickButton();
        page.checkHouseHand(5, 7, 5);
        page.checkPlayerWins();
        page.tapPlayButton();
        page.checkPlayerHand(5, 10);
        page.checkRefreshGameState();
    }
}
