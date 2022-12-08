package com.example.justmathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import com.example.justmathapp.databinding.ActivityGameBinding;

import java.text.MessageFormat;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding gameBinding ;
    private int seconds = 30 ;
    private Boolean stopTimer  = false;
    private Boolean isResultCorrect ;
    private int score = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameBinding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(gameBinding.getRoot());
        gameBinding.timer.setOnClickListener( v -> {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    seconds--;
                    if(seconds < 0){
                        Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                        intent.putExtra("score",score);
                        startActivity(intent);
                        stopTimer =true ;
                    }
                    if(!stopTimer){
                        handler.postDelayed(this,1000);
                    }
                }
            });
        });
        gameBinding.btnCorrect.setOnClickListener(v -> verifyAnswer(true));
        gameBinding.btnIncorrect.setOnClickListener(c -> verifyAnswer(false));
        generateQuestion();
    }
    private void generateQuestion(){
        isResultCorrect = true ;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int result = a + b ;
        float f = random.nextFloat() ;
        if(f  > 0.5f ){
            result = random.nextInt(100);
            isResultCorrect = false ;
        }
        gameBinding.txt1.setText(MessageFormat.format("{0} + {1}", a, b));
        gameBinding.txt2.setText(MessageFormat.format(" = {0}", result));
    }
    private void verifyAnswer(boolean answer){
        if(answer == isResultCorrect){
            score += 5 ;
            gameBinding.scores.setText(MessageFormat.format("Score : {0}",score));
        }else{
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        generateQuestion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer= false ;
        finish();
    }
}