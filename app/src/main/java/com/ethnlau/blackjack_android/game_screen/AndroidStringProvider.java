package com.ethnlau.blackjack_android.game_screen;

import android.content.Context;

import com.ethnlau.blackjack_android.R;

import blackjack_core.GameScreenContract;

public class AndroidStringProvider implements GameScreenContract.StringProvider {

    private final Context context;

    public AndroidStringProvider(final Context context) {
        this.context = context;
    }

    @Override
    public String getGameInstructions() {
        return context.getString(R.string.game_instructions);
    }

    @Override
    public String getStartingInstructions() {
        return context.getString(R.string.starting_instructions);
    }

    @Override
    public String getPlayAgainInstructions() {
        return context.getString(R.string.play_again_instructions);
    }

    @Override
    public String getPlayerBustAlert() {
        return context.getString(R.string.player_bust_alert);
    }

    @Override
    public String getUnderMinThresholdAlert() {
        return context.getString(R.string.house_under_min_threshold_alert);
    }

    @Override
    public String getHouseBustAlert() {
        return context.getString(R.string.house_bust_alert);
    }

    @Override
    public String getHouseAtLeastThresholdAlert() {
        return context.getString(R.string.house_at_least_min_threshold_alert);
    }
}
