package com.example.quizhalloween.quest;

import java.util.ArrayList;

public class Question {

    public String textQuest;
    public ArrayList<Answer> answers;

    public Question() {

    }

    // конструктор, принимающий два параметра
    public Question(String text, ArrayList<Answer> answers) {
        this.textQuest = text;
        this.answers = answers;
    }

    public String getTextQuest() {
        return this.textQuest;
    }

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }

    public boolean checkAnswer(String textAnswer) {
        boolean check = false;
        for (Answer ans : answers) {
            if (ans.getTextAnswer().equals(textAnswer)) check = ans.getIsAnswTrue();
        }
        return check;
    }
}
