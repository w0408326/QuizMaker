package com.example.quizmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> definitions = new ArrayList<String>(10);
    ArrayList<String> terms = new ArrayList<String>(10);

    Map<String,String> defTermMap = new HashMap<>();

    Iterator<String> defIterator;

    String answer;

    String name;

    int score = 0;

    int maxScore;
//    Iterator<String> termIterator = defTermMap.values().iterator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        Toast toast = Toast.makeText(getApplicationContext(),name, Toast.LENGTH_SHORT);
        toast.show();

        String str = "";
        BufferedReader br = null;
        InputStream is = null;

        is = getResources().openRawResource(R.raw.test);
        br = new BufferedReader(new InputStreamReader(is));
        TextView t = findViewById(R.id.textView2);

        try{
            while((str=br.readLine())!=null)
            {
                String[] definitionTerm = str.split(",");

                definitions.add(definitionTerm[0]);
                terms.add(definitionTerm[1]);

                defTermMap.put(definitionTerm[0],definitionTerm[1]);

            }
        }catch(IOException e)
        {
            System.out.println("Error reading file");
        }

        Collections.shuffle(definitions);

        maxScore = definitions.size();

        randomize();
    }//on create end

    private void randomize(){
        TextView t = findViewById(R.id.textView2);

        answer = definitions.get(0);
        definitions.remove(0);

        t.setText(answer);

        ArrayList<String> termButtons = new ArrayList<>();
        termButtons.add(defTermMap.get(answer));

        Random random = new Random();
        int j = 0;

        while(j<3){
            int r = random.nextInt(terms.size());

            if(!termButtons.contains(terms.get(r))){
                termButtons.add(terms.get(r));
                j++;
            }

        }

        // Shuffle the collection
        Collections.shuffle(termButtons);

        List<Button> buttons = new ArrayList<>();
        buttons.add((Button)findViewById(R.id.button7));
        buttons.add((Button)findViewById(R.id.button8));
        buttons.add((Button)findViewById(R.id.button9));
        buttons.add((Button)findViewById(R.id.button10));

        for (int i = 0; i < termButtons.size(); i++) {
            buttons.get(i).setText(termButtons.get(i));
        }
    }// end rerandomize()

    public void buttonClicked(View v){
        Button b = (Button)v;
        Context context = getApplicationContext();

        if(b.getText().equals(defTermMap.get(answer))){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,"CORRECT",duration);
            toast.show();

            score++;
        }else{
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,"WRONG",duration);
            toast.show();
        }

        if(!definitions.isEmpty()){
            randomize();
        }else{
            Intent myIntent = new Intent(getBaseContext(),score_screen.class);
            myIntent.putExtra("name",name);
            myIntent.putExtra("maxScore",maxScore);
            myIntent.putExtra("myScore",score);

            startActivity(myIntent);

        }
    }
}
