package com.example.justmathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.justmathapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain") ;
            intent.putExtra(Intent.EXTRA_TEXT ,"JustMaths - Learn Maths .  http://www.play.google.com");
            startActivity(intent);
        });
        activityMainBinding.play.setOnClickListener( v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class) ;
            startActivity(intent);
        });
        activityMainBinding.rating.setOnClickListener(v -> {
            Toast.makeText(this, "Go to google play store for rating the app", Toast.LENGTH_SHORT).show();
        });
    }
}