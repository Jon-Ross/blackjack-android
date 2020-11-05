package com.ethnlau.blackjack_android.game_screen;

import com.ethnlau.blackjack_android.blackjack_core.Dealer;
import com.ethnlau.blackjack_android.blackjack_core.Game;
import com.ethnlau.blackjack_android.blackjack_core.GamePresenter;
import com.ethnlau.blackjack_android.blackjack_core.GameScreenContract;

public class GameServiceLocator {

    GameScreenContract.Presenter getPresenter() {
        final Dealer dealer = new Dealer();
        final Game game = new Game(dealer);
        return new GamePresenter(game, new AndroidStringProvider());
    }
}
