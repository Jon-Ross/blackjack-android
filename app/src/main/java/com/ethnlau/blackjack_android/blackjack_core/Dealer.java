package com.ethnlau.blackjack_android.blackjack_core;

import java.util.Random;

public class Dealer {

    public int dealCard() {
        final Random rand = new Random();
        return rand.nextInt(11) + 1;
    }
}
