package com.ethnlau.blackjack_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "*****  onCreate");
        setContentView(R.layout.activity_main);

        final TextView textHello = findViewById(R.id.textHello);
        textHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View tab) {
                final Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "*****  onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "*****  onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "*****  onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "*****  onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "*****  onDestroy");
    }
}