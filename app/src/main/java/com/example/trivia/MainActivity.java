package com.example.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startTrivia);
        EditText nameInput = findViewById(R.id.name);

        startButton.setOnClickListener(v -> {
            String textName = String.valueOf(nameInput.getText());

            if(textName.isEmpty()) {
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("PlayerName", textName);
                startActivity(intent);
                finish();
            }
        });
    }
}