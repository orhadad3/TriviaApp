package com.example.trivia;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        // Get Name, Score and Size of questions from Quiz Class
        String playerName = getIntent().getStringExtra("PlayerName");
        int score = getIntent().getIntExtra("Score", 0);
        int sumQuest = getIntent().getIntExtra("questionsNumber", 0);

        TextView statusText = findViewById(R.id.statusText);
        ImageView status = findViewById(R.id.status);
        double percent = Double.parseDouble(new DecimalFormat("##.##").format(((double) score /sumQuest) * 100));

        String niceJob = playerName + " Nice Job!\n" + "Your Score is: " + score + "/" + sumQuest + " (" + percent + "%)";
        String tryAgain = playerName + " You can do more!\n" + "Your Score is: " + score + "/" + sumQuest + " (" + percent + "%)\nTry Again :)";

        if(percent >= 50) {
            status.setImageResource(R.drawable.good);
            statusText.setText(niceJob);
        } else {
            status.setImageResource(R.drawable.try_again);
            statusText.setText(tryAgain);
        }
    }
}