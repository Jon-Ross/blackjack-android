package com.ethnlau.blackjack_android;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ethnlau.blackjack_android.game_screen.DealerProvider;
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

        // tap PLAY GAME
        onView(withText("PLAY GAME"))
                .perform(click());

        // check player hand is [10, 2] and house hand is empty
        onView(withId(R.id.playerHand))
                .check(matches(withText("[10, 2]")));
        onView(withId(R.id.houseHand)).check(matches(withText("")));

        // tap TWIST
        onView(withId(R.id.twistButton)).perform(click());
        // check player hand is [10, 2, 7]
        onView(withId(R.id.playerHand))
                .check(matches(withText("[10, 2, 7]")));
        // tap STICK
        onView(withId(R.id.stickButton)).perform(click());
        // check house hand is [5, 7, 5]
        onView(withId(R.id.houseHand))
                .check(matches(withText("[5, 7, 5]")));
        // check player wins
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
        // tap PLAY GAME
        onView(withText("PLAY GAME"))
                .perform(click());
        // check player hand is [5, 10] and house hand is empty
        onView(withId(R.id.playerHand))
                .check(matches(withText("[5, 10]")));
        onView(withId(R.id.movesHistory))
                .check(matches(withText("")));
        onView(withId(R.id.houseHand))
                .check(matches(withText("")));
        onView(withId(R.id.winner))
                .check(matches(withText("")));
    }
}
