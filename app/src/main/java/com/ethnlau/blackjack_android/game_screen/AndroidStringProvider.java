package com.ethnlau.blackjack_android.game_screen;

import com.ethnlau.blackjack_android.blackjack_core.GameScreenContract;

public class AndroidStringProvider implements GameScreenContract.StringProvider {

    @Override
    public String getGameInstructions() {
        return "Stick or twist";
    }

    @Override
    public String getStartingInstructions() {
        return "Start a new game";
    }

    @Override
    public String getPlayAgainInstructions() {
        return "Play again";
    }

    @Override
    public String getPlayerBustAlert() {
        return "You've gone bust!";
    }

    @Override
    public String getUnderMinThresholdAlert() {
        return "House is less that 17. House twists";
    }

    @Override
    public String getHouseBustAlert() {
        return "House has gone bust!";
    }

    @Override
    public String getHouseAtLeastThresholdAlert() {
        return "House is at least 17";
    }
}
