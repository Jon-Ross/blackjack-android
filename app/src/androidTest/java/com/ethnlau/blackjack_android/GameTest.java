package com.ethnlau.blackjack_android;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ethnlau.blackjack_android.game_screen.DealerProvider;
import com.ethnlau.blackjack_android.game_screen.GameActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import blackjack_core.Hand;

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

    @Test
    public void GivenPlayerHandBetterThanHouse_WhenRunGame_ThenPlayerWinsAndCanPlayAgain() {
        final DealerProvider dealerProvider = DealerProvider.getInstance();
        AndroidDealerMock dealer = new AndroidDealerMock();
        dealer.addValue(10);
        dealer.addValue(2);
        dealer.addValue(7);
        dealer.addValue(5);
        dealer.addValue(7);
        dealer.addValue(5);
        dealer.addValue(5);
        dealer.addValue(10);
        dealerProvider.setDealer(dealer);

        mActivityTestRule.launchActivity(new Intent());

        checkStartingGameState();
        tapPlayButton();
        checkPlayerHand(10, 2);
        checkHouseHand();
        tapTwistButton();
        checkPlayerHand(10, 2, 7);
        tapStickButton();
        checkHouseHand(5, 7, 5);
        checkPlayerWins();
        tapPlayButton();
        checkPlayerHand(5, 10);
        checkRefreshGameState();
    }

    private void checkStartingGameState() {
        onView(withId(R.id.instructions))
                .check(matches(withText("Tap on \"PLAY GAME\" to start a new game")));
        onView(withId(R.id.movesHistory))
                .check(matches(withText("")));
        onView(withId(R.id.playerHand))
                .check(matches(withText("")));
        onView(withId(R.id.houseHand))
                .check(matches(withText("")));
        onView(withId(R.id.winner))
                .check(matches(withText("")));
        onView(withId(R.id.stickButton))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.twistButton))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.playButton))
                .check(matches(isDisplayed()));
        // check other elements are reset to default
    }

    private void checkPlayerWins() {
        onView(withId(R.id.winner)).check(matches(isDisplayed()));
        onView(withId(R.id.winner)).check(matches(withText("Player wins!")));
        onView(withId(R.id.instructions))
                .check(matches(withText("Tap on \"PLAY GAME\" to start a new game")));
        onView(withId(R.id.stickButton))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.twistButton))
                .check(matches(not(isDisplayed())));
        onView(withId(R.id.playButton))
                .check(matches(isDisplayed()));
    }

    private void checkHouseHand(final String hand) {
        onView(withId(R.id.houseHand)).check(matches(withText(hand)));
    }

    private void checkHouseHand(final Integer... hand) {
        checkHouseHand(convertValuesToString(hand));
    }

    private void tapStickButton() {
        tapViewWithId(R.id.stickButton);
    }

    private void checkPlayerHand(final Integer... hand) {
        checkPlayerHand(convertValuesToString(hand));
    }

    private void checkPlayerHand(final String hand) {
        onView(withId(R.id.playerHand)).check(matches(withText(hand)));
    }

    private void tapTwistButton() {
        tapViewWithId(R.id.twistButton);
    }

    private void tapPlayButton() {
        tapViewWithText("PLAY GAME");
    }

    private void checkRefreshGameState() {
        onView(withId(R.id.movesHistory))
                .check(matches(withText("")));
        checkHouseHand();
        onView(withId(R.id.winner))
                .check(matches(withText("")));
    }

    private void tapViewWithText(final String text) {
        onView(withText(text)).perform(click());
    }

    private void tapViewWithId(@IdRes final int id) {
        onView(withId(id)).perform(click());
    }

    private String convertValuesToString(final Integer... hand) {
        if (hand.length > 0) {
            return Arrays.asList(hand).toString();
        } else {
            return "";
        }
    }
}
