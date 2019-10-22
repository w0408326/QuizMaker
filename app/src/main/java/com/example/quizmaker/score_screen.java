package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class score_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int maxScore = intent.getIntExtra("maxScore",0);
        int myScore = intent.getIntExtra("myScore",0);

        TextView t = findViewById(R.id.scoreTextView);

        t.setText(name + ":" + myScore + "/" + maxScore);
    }
}
