package com.example.quizhalloween;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizhalloween.OnSelectedButtonListener;
import com.example.quizhalloween.R;
import com.example.quizhalloween.quest.Question;
import com.example.quizhalloween.quest.QuestionGenerator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class FragmentQuiz extends Fragment implements View.OnClickListener {

    public static final String KEY_RESULT = "key_result";
    private View quizView;
    private TextView text_question, numQuest;
    private Button btn1, btn2, btn3, btn4, btnNext;
    private String question, ans_1, ans_2, ans_3, ans_4;
    private ArrayList<Question> questions = new ArrayList<>();
    private int index = -1;
    private int result = 0;
    private boolean chek_1, chek_2, chek_3, chek_4;

    DatabaseReference database;
    OnSelectedButtonListener onSelectedButtonListener;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            onSelectedButtonListener = (OnSelectedButtonListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        quizView = inflater.inflate(R.layout.fragment_quiz, container, false);

        text_question = quizView.findViewById(R.id.text_question);
        numQuest = quizView.findViewById(R.id.numQuest);
        btn1 = quizView.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = quizView.findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = quizView.findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = quizView.findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btnNext = quizView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

//        если без базы данных
//        questions = QuestionGenerator.getQuestions();

//        передача вопросов с ответами в Firebase
//        quest = QuestionGenerator.getQuestions();
//        FirebaseAdapter firebaseAdapter = new FirebaseAdapter();
//        firebaseAdapter.writeQuest(quest);

        database = FirebaseDatabase.getInstance().getReference().child("quest");
        changeQuest();
        return quizView;
    }

    private void changeQuest() {
        index++;
        if (index < 10) {
//            если без базы данных
//            updateQuestions();

            getQuestion();
            btnNext.setVisibility(quizView.INVISIBLE);
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            btn4.setEnabled(true);
            btn1.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn2.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn3.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn4.setBackground(getResources().getDrawable(R.drawable.button_equal));
        } else {
            updateResult();
            onSelectedButtonListener.onButtonSelected(5); // результат
        }
    }

    private void getQuestion() {
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Question quest = snapshot.getValue(Question.class);
                questions.add(new Question(quest.getTextQuest(), quest.getAnswers()));
                updateQuestions();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void updateQuestions() {

        numQuest.setText("Вопрос: " + (index + 1) + "/10");
        question = questions.get(index).getTextQuest();
        text_question.setText(question);
        ans_1 = questions.get(index).getAnswers().get(0).getTextAnswer();
        btn1.setText(ans_1);
        ans_2 = questions.get(index).getAnswers().get(1).getTextAnswer();
        btn2.setText(ans_2);
        ans_3 = questions.get(index).getAnswers().get(2).getTextAnswer();
        btn3.setText(ans_3);
        ans_4 = questions.get(index).getAnswers().get(3).getTextAnswer();
        btn4.setText(ans_4);
    }

    private void updateResult() {
        Bundle resultVol = new Bundle();
        resultVol.putInt(KEY_RESULT, result);
        Intent intent = getActivity().getIntent();
        intent.putExtras(resultVol);
    }

    private void buttonsEn() {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btnNext.setVisibility(quizView.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                chek_1 = questions.get(index).checkAnswer(ans_1);
                if (chek_1 == true) {
                    btn1.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn1.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
            case R.id.btn2:
                chek_2 = questions.get(index).checkAnswer(ans_2);
                if (chek_2 == true) {
                    btn2.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn2.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
            case R.id.btn3:
                chek_3 = questions.get(index).checkAnswer(ans_3);
                if (chek_3 == true) {
                    btn3.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn3.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
            case R.id.btn4:
                chek_4 = questions.get(index).checkAnswer(ans_4);
                if (chek_4 == true) {
                    btn4.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn4.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
            case R.id.btnNext:
                changeQuest();
                break;
        }
    }
}