package com.ethnlau.blackjack_android.game_screen;

import android.content.Context;

import com.ethnlau.blackjack_android.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import blackjack_core.GameScreenContract;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AndroidStringProviderTest {

    private static final String GAME_INSTRUCTIONS = "Stick and twist";
    private static final String STARTING_INSTRUCTIONS = "Start a new blackjack game";
    private static final String HOUSE_VALUE_IS_AT_LEAST_THRESHOLD = "House value is at least threshold";
    private static final String HOUSE_UNDER_MIN_THRESHOLD_ALERT = "House value is less than 17. House Twists.";
    private static final String PLAYER_BUST_ALERT = "You're bust!";
    private static final String HOUSE_BUST_ALERT = "House bust!";

    @Mock
    private Context context;

    private GameScreenContract.StringProvider stringProvider;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        stringProvider = new AndroidStringProvider(context);

        when(context.getString(R.string.starting_instructions)).thenReturn(STARTING_INSTRUCTIONS);
        when(context.getString(R.string.game_instructions)).thenReturn(GAME_INSTRUCTIONS);
        when(context.getString(R.string.player_bust_alert)).thenReturn(PLAYER_BUST_ALERT);
        when(context.getString(R.string.house_bust_alert)).thenReturn(HOUSE_BUST_ALERT);
        when(context.getString(R.string.house_under_min_threshold_alert)).thenReturn(HOUSE_UNDER_MIN_THRESHOLD_ALERT);
        when(context.getString(R.string.house_at_least_min_threshold_alert)).thenReturn(HOUSE_VALUE_IS_AT_LEAST_THRESHOLD);
    }

    @Test
    public void testGetGameInstructionsStringValues() {
        assertEquals(GAME_INSTRUCTIONS, stringProvider.getGameInstructions());
    }

    @Test
    public void testGetStartingInstructionsStringValues() {
        assertEquals(STARTING_INSTRUCTIONS, stringProvider.getStartingInstructions());
    }

    @Test
    public void testGetPlayerBustAlertStringValues() {
        assertEquals(PLAYER_BUST_ALERT, stringProvider.getPlayerBustAlert());
    }

    @Test
    public void testGetUnderMinThresholdAlertStringValues() {
        assertEquals(HOUSE_UNDER_MIN_THRESHOLD_ALERT, stringProvider.getUnderMinThresholdAlert());
    }

    @Test
    public void testGetHouseBustAlertStringValues() {
        assertEquals(HOUSE_BUST_ALERT, stringProvider.getHouseBustAlert());
    }

    @Test
    public void testGetHouseAtLeastThresholdAlertStringValues() {
        assertEquals(HOUSE_VALUE_IS_AT_LEAST_THRESHOLD, stringProvider.getHouseAtLeastThresholdAlert());
    }
}