package com.ethnlau.blackjack_android.game_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ethnlau.blackjack_android.R;

import blackjack_core.GameScreenContract;
import blackjack_core.Hand;
import blackjack_core.Winner;

public class GameActivity extends AppCompatActivity implements GameScreenContract.View {

    private GameScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new GameServiceLocator().getPresenter();
        presenter.bind(this);
        presenter.onStartScreen();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void showStartingInstructions(String instructions) {
        final TextView instructionsView = findViewById(R.id.instructions);
        instructionsView.setText(instructions);
    }

    @Override
    public void showPlayerHand(Hand hand) {

    }

    @Override
    public void showGameInstructions(String instructions) {

    }

    @Override
    public void showHouseHand(Hand hand) {

    }

    @Override
    public void showWinner(Winner winner) {

    }

    @Override
    public void showAlert(String alert) {

    }
}