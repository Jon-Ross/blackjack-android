package com.ethnlau.blackjack_android.game_screen;

import blackjack_core.Dealer;
import blackjack_core.Game;
import blackjack_core.GamePresenter;
import blackjack_core.GameScreenContract;

public class GameServiceLocator {

    GameScreenContract.Presenter getPresenter() {
        final Dealer dealer = new Dealer();
        final Game game = new Game(dealer);
        return new GamePresenter(game, new AndroidStringProvider());
    }
}
