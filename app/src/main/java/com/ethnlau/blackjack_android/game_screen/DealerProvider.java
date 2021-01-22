package com.ethnlau.blackjack_android.game_screen;

import android.os.DeadObjectException;

import blackjack_core.Dealer;

public class DealerProvider {

    public static DealerProvider instance;

    private Dealer dealer;

    private DealerProvider() {
        dealer = new Dealer();
    }

    public static DealerProvider getInstance() {
        if (instance == null) {
            instance = new DealerProvider();
        }
        return instance;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
