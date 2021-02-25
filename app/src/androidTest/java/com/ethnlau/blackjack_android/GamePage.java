package com.ethnlau.blackjack_android;

import androidx.annotation.IdRes;

import com.ethnlau.blackjack_android.game_screen.DealerProvider;

import java.util.Arrays;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class GamePage {

    void setUpDealerMock(final int... cardValues) {
        final DealerProvider dealerProvider = DealerProvider.getInstance();
        AndroidDealerMock dealer = new AndroidDealerMock();
        for (int value : cardValues) {
            dealer.addValue(value);
        }
        dealerProvider.setDealer(dealer);
    }

    void checkStartingGameState() {
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

    void checkPlayerWins() {
        checkWinner("Player");
    }

    public void checkHouseWins() {
        checkWinner("House");
    }

    private void checkWinner(final String winner) {
        onView(withId(R.id.winner)).check(matches(isDisplayed()));
        onView(withId(R.id.winner)).check(matches(withText(winner + " wins!")));
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

    void checkHouseHand(final Integer... hand) {
        checkHouseHand(convertValuesToString(hand));
    }

    void tapStickButton() {
        tapViewWithId(R.id.stickButton);
    }

    void checkPlayerHand(final Integer... hand) {
        checkPlayerHand(convertValuesToString(hand));
    }

    private void checkPlayerHand(final String hand) {
        onView(withId(R.id.playerHand)).check(matches(withText(hand)));
    }

    void checkMovesHistory(String movesHistory) {
        onView(withId(R.id.movesHistory))
                .check(matches(withText(movesHistory)));
    }

    void tapTwistButton() {
        tapViewWithId(R.id.twistButton);
    }

    void checkRefreshGameState() {
        onView(withId(R.id.movesHistory))
                .check(matches(withText("")));
        checkHouseHand();
        onView(withId(R.id.winner))
                .check(matches(withText("")));
    }

    void tapPlayButton() {
        tapViewWithText("PLAY GAME");
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
