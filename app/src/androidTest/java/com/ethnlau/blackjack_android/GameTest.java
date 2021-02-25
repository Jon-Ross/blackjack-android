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

    private static final String PLAYER_TWISTS = "Player twists";
    private static final String PLAYER_STICKS = "Player sticks";
    private static final String HOUSE_IS_LESS_THAN_17 = "House is less than 17.";
    private static final String HOUSE_TWISTS = "House twists";
    private static final String HOUSE_IS_AT_LEAST_17 = "House is at least 17.";
    private static final String HOUSE_STICKS = "House sticks";
    private static final String YOU_VE_GONE_BUST = "You've gone bust!";

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
        page.checkMovesHistory(
                "\n" + PLAYER_TWISTS + "\n" +
                        PLAYER_STICKS + "\n" +
                        HOUSE_IS_LESS_THAN_17 + "\n" +
                        HOUSE_TWISTS + "\n" +
                        HOUSE_IS_AT_LEAST_17 + "\n" +
                        HOUSE_STICKS
        );
        page.tapPlayButton();
        page.checkPlayerHand(5, 10);
        page.checkRefreshGameState();
    }

    @Test
    public void GivenPlayerGoesBust_WhenRunGame_ThenHouseWinsAndCanPlayAgain() {
        page.setUpDealerMock(10, 5, 7, 1, 2);

        mActivityTestRule.launchActivity(new Intent());

        page.checkStartingGameState();
        page.tapPlayButton();
        page.checkPlayerHand(10, 5);
        page.checkHouseHand();
        page.tapTwistButton();
        page.checkPlayerHand(10, 5, 7);
        page.checkHouseWins();
        page.checkMovesHistory(
                "\n" + PLAYER_TWISTS + "\n" +
                        YOU_VE_GONE_BUST
        );
        page.tapPlayButton();
        page.checkPlayerHand(1, 2);
        page.checkRefreshGameState();
    }

    @Test
    public void GivenHouseHandBetterThanPlayer_WhenRunGame_ThenHouseWinsAndCanPlayAgain() {
        page.setUpDealerMock(5, 5, 7, 10, 2, 3);

        mActivityTestRule.launchActivity(new Intent());

        page.checkStartingGameState();
        page.tapPlayButton();
        page.checkPlayerHand(5, 5);
        page.checkHouseHand();
        page.tapStickButton();
        page.checkHouseHand(7, 10);
        page.checkHouseWins();
        page.checkMovesHistory(
                "\n" + PLAYER_STICKS + "\n" +
                        HOUSE_IS_AT_LEAST_17 + "\n" +
                        HOUSE_STICKS
        );
        page.tapPlayButton();
        page.checkPlayerHand(2, 3);
        page.checkRefreshGameState();
    }
}
