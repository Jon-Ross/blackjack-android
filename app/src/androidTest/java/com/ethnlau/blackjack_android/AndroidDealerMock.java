package com.ethnlau.blackjack_android;

import blackjack_core.Dealer;

public class AndroidDealerMock extends Dealer {

    private final int[] values = new int[15];

    private int counter = 0;
    private int removePosition = 0;

    @Override
    public int dealCard() {
        return values[removePosition++];
    }

    public void addValue(int value) {
        values[counter++] = value;
    }
}