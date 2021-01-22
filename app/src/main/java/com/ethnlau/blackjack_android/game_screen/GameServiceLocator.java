package com.ethnlau.blackjack_android.game_screen;

import android.content.Context;

import blackjack_core.Dealer;
import blackjack_core.Game;
import blackjack_core.GamePresenter;
import blackjack_core.GameScreenContract;

public class GameServiceLocator {

    GameScreenContract.Presenter getPresenter(final Context context) {
        final DealerProvider dealerProvider = DealerProvider.getInstance();
        final Game game = new Game(dealerProvider.getDealer());
        return new GamePresenter(game, new AndroidStringProvider(context));
    }
}
