package com.example.justmathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.justmathapp.databinding.ActivityScoreBinding;

import java.text.MessageFormat;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding scoreBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoreBinding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(scoreBinding.getRoot());

        int scores = getIntent().getIntExtra("score",0);
        scoreBinding.score.setText(MessageFormat.format("Current Score : {0}",scores));

        scoreBinding.share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,MessageFormat.format("Your Total Score Of the Game is :: {0}",scores));
            startActivity(intent);
        });
    }
}