package com.example.quizhalloween.quest;

public class Answer {
    String textAnswer;
    String ans1, ans2, ans3, ans4;
    boolean isRight;

    public Answer(){

    }

    public  Answer(String ans1,String ans2, String ans3, String ans4){
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    public Answer(String textAnswer){
        this.textAnswer = textAnswer;
    }

    public Answer(String textA, boolean isRight) {
        this.textAnswer = textA;
        this.isRight = isRight;
    }

    public String getAns1() {
        return this.ans1;
    }

    public String getAns2() {
        return this.ans2;
    }

    public String getAns3() {
        return this.ans3;
    }

    public String getAns4() {
        return this.ans4;
    }

    public boolean getIsAnswTrue() {
        return this.isRight;
    }

    public String getTextAnswer() {
        return this.textAnswer;
    }

}
