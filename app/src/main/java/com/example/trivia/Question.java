package com.example.trivia;

public class Question {
    private final String quest;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String correctAnswer;

    public Question(String quest, String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
        this.quest = quest;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }
    public String getQuest() {
        return this.quest;
    }
    public String getAnswer1() {
        return this.answer1;
    }
    public String getAnswer2() {
        return this.answer2;
    }
    public String getAnswer3() {
        return this.answer3;
    }
    public String getAnswer4() {
        return this.answer4;
    }
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }
}