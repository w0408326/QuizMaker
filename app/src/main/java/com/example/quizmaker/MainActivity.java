package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = "";
        BufferedReader br = null;
        InputStream is = null;

        is = getResources().openRawResource(R.raw.test);
        br = new BufferedReader(new InputStreamReader(is));
        TextView t = findViewById(R.id.textView2);

        ArrayList<String> definitions = new ArrayList<String>(10);
        ArrayList<String> terms = new ArrayList<String>(10);
        try{
            while((str=br.readLine())!=null)
            {
                String[] definitionTerm = str.split(",");

                definitions.add(definitionTerm[0]);
                terms.add(definitionTerm[1]);


            }
        }catch(IOException e)
        {
            System.out.println("Error reading file");
        }

//        for (String temp : definitions){
//            t.append(temp);
//        }

   //     TODO: HashMap
    }
}
