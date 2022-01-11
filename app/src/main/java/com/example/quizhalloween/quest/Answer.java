package com.example.quizhalloween.quest;

public class Answer {
    String textAnswer;
    boolean isAnswTrue;

    public Answer(){

    }

    public Answer(String textAnswer, boolean isAnswTrue) {
        this.textAnswer = textAnswer;
        this.isAnswTrue = isAnswTrue;
    }

    public boolean getIsAnswTrue() {
        return this.isAnswTrue;
    }

    public String getTextAnswer() {
        return this.textAnswer;
    }

}
