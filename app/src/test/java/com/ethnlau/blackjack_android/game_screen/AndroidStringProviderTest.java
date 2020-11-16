package com.ethnlau.blackjack_android.game_screen;

import com.ethnlau.blackjack_android.blackjack_core.GameScreenContract;

import org.junit.Test;

import static org.junit.Assert.*;

public class AndroidStringProviderTest {

    private final GameScreenContract.StringProvider stringProvider = new AndroidStringProvider();

    @Test
    public void testGetGameInstructionsStringValues() {
        assertEquals("Stick or twist", stringProvider.getGameInstructions());
    }

    @Test
    public void testGetStartingInstructionsStringValues() {
        assertEquals("Start a new game", stringProvider.getStartingInstructions());
    }

    @Test
    public void testGetPlayAgainInstructionsStringValues() {
        assertEquals("Play again", stringProvider.getPlayAgainInstructions());
    }

    @Test
    public void testGetPlayerBustAlertStringValues() {
        assertEquals("You've gone bust!", stringProvider.getPlayerBustAlert());
    }

    @Test
    public void testGetUnderMinThresholdAlertStringValues() {
        assertEquals("House is less that 17. House twists", stringProvider.getUnderMinThresholdAlert());
    }

    @Test
    public void testGetHouseBustAlertStringValues() {
        assertEquals("House has gone bust!", stringProvider.getHouseBustAlert());
    }

    @Test
    public void testGetHouseAtLeastThresholdAlertStringValues() {
        assertEquals("House is at least 17", stringProvider.getHouseAtLeastThresholdAlert());
    }
}