package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class welcome_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void beginOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),MainActivity.class);

        EditText t = findViewById(R.id.nameTextView);

        myIntent.putExtra("name",t.getText().toString());

        startActivity(myIntent);
    }
}
