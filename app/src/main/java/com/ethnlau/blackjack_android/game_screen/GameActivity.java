package com.ethnlau.blackjack_android.game_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ethnlau.blackjack_android.R;

import blackjack_core.GameScreenContract;
import blackjack_core.Hand;
import blackjack_core.Winner;

public class GameActivity extends AppCompatActivity implements GameScreenContract.View {

    private GameScreenContract.Presenter presenter;

    private TextView instructionsView;
    private TextView movesHistoryView;
    private TextView playerHandView;
    private TextView houseHandView;
    private TextView winnerView;

    private Button playButton;
    private Button stickButton;
    private Button twistButton;

    private ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new GameServiceLocator().getPresenter(this);
        presenter.bind(this);

        setUpViews();

        presenter.onStartScreen();
    }

    private void setUpViews() {
        setUpTextViews();
        setUpButtons();
        scrollview = findViewById(R.id.movesHistoryScroll);
    }

    private void setUpTextViews() {
        instructionsView = findViewById(R.id.instructions);
        movesHistoryView = findViewById(R.id.movesHistory);
        playerHandView = findViewById(R.id.playerHand);
        houseHandView = findViewById(R.id.houseHand);
        winnerView = findViewById(R.id.winner);
    }

    private void setUpButtons() {
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            movesHistoryView.setText("");
            presenter.onStartBlackJackGame();
        });

        stickButton = findViewById(R.id.stickButton);
        stickButton.setOnClickListener(v -> {
            updateMovesHistory("Player sticks");
            presenter.onStick();
        });

        twistButton = findViewById(R.id.twistButton);
        twistButton.setOnClickListener(v -> {
            updateMovesHistory("Player twists");
            presenter.onTwist();
        });
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void showStartingInstructions(String instructions) {
        instructionsView.setText(instructions);
        playButton.setVisibility(View.VISIBLE);
        stickButton.setVisibility(View.GONE);
        twistButton.setVisibility(View.GONE);
    }

    @Override
    public void showGameInstructions(String instructions) {
        instructionsView.setText(instructions);
        playButton.setVisibility(View.GONE);
        stickButton.setVisibility(View.VISIBLE);
        twistButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPlayerHand(Hand hand) {
        playerHandView.setText(hand.toString());
        winnerView.setVisibility(View.GONE);
        winnerView.setText("");
        houseHandView.setText("");
    }

    @Override
    public void showHouseHand(Hand hand) {
        houseHandView.setText(hand.toString());
    }

    @Override
    public void showWinner(Winner winner) {
        if (winner == Winner.PLAYER) {
            winnerView.setText(R.string.player_wins);
        } else {
            winnerView.setText(R.string.house_wins);
        }
        winnerView.setVisibility(View.VISIBLE);
        instructionsView.setText(R.string.starting_instructions);
        playButton.setVisibility(View.GONE);
        stickButton.setVisibility(View.VISIBLE);
        twistButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAlert(String alert) {
        updateMovesHistory(alert);
    }

    private void updateMovesHistory(String move) {
        final String currentMoves = movesHistoryView.getText().toString();
        movesHistoryView.setText((currentMoves + "\n" + move).trim());

        scrollview.post(() -> scrollview.fullScroll(ScrollView.FOCUS_DOWN));
    }
}
