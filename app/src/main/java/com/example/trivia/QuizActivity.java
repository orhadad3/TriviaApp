package com.example.trivia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questions; // All Questions
    private int score = 0; // Score Member
    private int correctQuestionIndex = 0;
    private CountDownTimer countDownTimer;
    private static final long TIMER_DURATION = 30000; // Timer - 30 seconds
    private static final int MAX_PROGRESS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        RadioGroup answersRadio = findViewById(R.id.answers);
        questions = getQuestions(); // Insert all questions

        if(questions.isEmpty()) { // Check Questions list
            Toast.makeText(this, "No questions available!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showQuestion();

        answersRadio.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId != -1) {
                RadioButton selectedAnswer = findViewById(checkedId);
                boolean isCorrect = selectedAnswer.getText().toString().equals(questions.get(correctQuestionIndex).getCorrectAnswer());
                if(isCorrect) score++; // Update Score
                nextQuest();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showQuestion() {
        Question currentQuestion = questions.get(correctQuestionIndex);
        TextView questText = findViewById(R.id.questionText);
        RadioButton answer1 = findViewById(R.id.answer1);
        RadioButton answer2 = findViewById(R.id.answer2);
        RadioButton answer3 = findViewById(R.id.answer3);
        RadioButton answer4 = findViewById(R.id.answer4);
        RadioGroup answersGroup = findViewById(R.id.answers);

        questText.setText(currentQuestion.getQuest());
        answer1.setText(currentQuestion.getAnswer1());
        answer2.setText(currentQuestion.getAnswer2());
        answer3.setText(currentQuestion.getAnswer3());
        answer4.setText(currentQuestion.getAnswer4());
        answersGroup.clearCheck();

        TextView questNum = findViewById(R.id.numQuest);
        questNum.setText("Question " + (correctQuestionIndex + 1) + " of " + questions.size());
        startCircularTimer();
    }

    private void startCircularTimer() {
        TextView timerText = findViewById(R.id.timerText);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(MAX_PROGRESS);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        progressBar.setProgress(MAX_PROGRESS);

        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) ((millisUntilFinished * MAX_PROGRESS) / TIMER_DURATION);

                if (millisUntilFinished < 1000) {
                    progress = 0;
                }

                progressBar.setProgress(progress); // Update line
                timerText.setText((millisUntilFinished / 1000) + "s");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                timerText.setText("0s");
                nextQuest();
            }
        }.start();
    }

    private void nextQuest() {
        correctQuestionIndex++;

        if (correctQuestionIndex < questions.size()) {
            showQuestion();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("PlayerName", getIntent().getStringExtra("PlayerName"));
        intent.putExtra("questionsNumber", questions.size());
        intent.putExtra("Score", score);
        startActivity(intent);
        finish();
    }

    private List<Question> getQuestions() {
        List<Question> questList = new ArrayList<>();
        questList.add(new Question("What is the capital of Israel?", "Paris", "Jerusalem", "London", "Berlin", "Jerusalem"));
        questList.add(new Question("What the answer of: 2 + 2?", "6", "2", "4", "9", "4"));
        questList.add(new Question("What is the capital of France?", "Rome", "Madrid", "Berlin", "Paris", "Paris"));
        questList.add(new Question("Which animal is known as “King of the Jungle”?", "Elephant", "Tiger", "Lion", "Giraffe", "Lion"));
        questList.add(new Question("What color is the sky on a clear day?", "Blue", "Green", "Red", "Yellow", "Blue"));
        questList.add(new Question("How many legs does a spider have?", "6", "4", "10", "8", "8"));
        return questList;
    }
}