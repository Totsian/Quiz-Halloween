package com.example.quizhalloween;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizhalloween.quest.Answer;
import com.example.quizhalloween.quest.Question;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentDoctor extends Fragment implements View.OnClickListener {

    public static final String KEY_RESULT = "key_result";
    OnSelectedButtonListener onSelectedButtonListener;
    private TextView time, numQuest, textQuest;
    private Button btn1, btn2, btn3, btn4, btnNext;
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Answer> answers = new ArrayList<>();
    private ArrayList<Answer> answersTrue = new ArrayList<>();
    private View viewDoctor;
    DatabaseReference database;
//    Handler h;
    private int result = 0;
    private int index = -1;

    private String question, ans_1, ans_2, ans_3, ans_4;
//    private CountDownTimer countDownTimer;

    @Override
    public void onAttach(@NonNull Context activity) {
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
        viewDoctor = inflater.inflate(R.layout.fragment_doctor, container, false);

//        time = viewDoctor.findViewById(R.id.timeD);
        numQuest = viewDoctor.findViewById(R.id.numQuestD);
        textQuest = viewDoctor.findViewById(R.id.text_question_d);

        btn1 = viewDoctor.findViewById(R.id.btn1_d);
        btn1.setOnClickListener(this);
        btn2 = viewDoctor.findViewById(R.id.btn2_d);
        btn2.setOnClickListener(this);
        btn3 = viewDoctor.findViewById(R.id.btn3_d);
        btn3.setOnClickListener(this);
        btn4 = viewDoctor.findViewById(R.id.btn4_d);
        btn4.setOnClickListener(this);
        btnNext = viewDoctor.findViewById(R.id.btnNext_d);
        btnNext.setOnClickListener(this);
//        h = new Handler();
        database = FirebaseDatabase.getInstance().getReference().child("Doc");
        changeQuest();
        return viewDoctor;
    }

    //    final Runnable updateTimer = new Runnable() {
//        @Override
//        public void run() {
    private void changeQuest() {
        index++;
        if (index < 10) {
//                timer();
            getQuestion();
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            btn4.setEnabled(true);
            btnNext.setVisibility(viewDoctor.INVISIBLE);
            btn1.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn2.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn3.setBackground(getResources().getDrawable(R.drawable.button_equal));
            btn4.setBackground(getResources().getDrawable(R.drawable.button_equal));
//                h.postDelayed(this, 10000);
//                countIt++;
        } else {
            updateResult();
            onSelectedButtonListener.onButtonSelected(5); // результат
        }
    }
//    };

//    private void timer() {
//        countDownTimer = new CountDownTimer(10000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                if (millisUntilFinished / 1000 <= 9)
//                    time.setText("0:0" + millisUntilFinished / 1000);
//                else
//                    time.setText("0:" + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                time.setText("0:30");
//            }
//        }.start();
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        h.postDelayed(updateTimer, 10);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        h.removeCallbacks(updateTimer);
//    }

    private void getQuestion() {
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Answer ans = snapshot.getValue(Answer.class);
                answers.add(new Answer(ans.getAns1(), ans.getAns2(), ans.getAns3(), ans.getAns4()));
                Answer trueAns = snapshot.getValue(Answer.class);
                answersTrue.add(new Answer(trueAns.getTextAnswer()));
                Question quest = snapshot.getValue(Question.class);
                questions.add(new Question(quest.getTextQuest(), answers));
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
        textQuest.setText(question);
        ans_1 = questions.get(index).getAnswers().get(index).getAns1();
        btn1.setText(ans_1);
        ans_2 = questions.get(index).getAnswers().get(index).getAns2();
        btn2.setText(ans_2);
        ans_3 = questions.get(index).getAnswers().get(index).getAns3();
        btn3.setText(ans_3);
        ans_4 = questions.get(index).getAnswers().get(index).getAns4();
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
        btnNext.setVisibility(viewDoctor.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn1_d:
                if (answersTrue.get(index).getTextAnswer().equals(ans_1)) {
                    btn1.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn1.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;

            case R.id.btn2_d:
                if (answersTrue.get(index).getTextAnswer().equals(ans_2)) {
                    btn2.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn2.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
//
            case R.id.btn3_d:
                if (answersTrue.get(index).getTextAnswer().equals(ans_3)) {
                    btn3.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn3.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;

            case R.id.btn4_d:
                if (answersTrue.get(index).getTextAnswer().equals(ans_4)) {
                    btn4.setBackground(getResources().getDrawable(R.drawable.next));
                    result++;
                } else {
                    btn4.setBackground(getResources().getDrawable(R.drawable.false_ans));
                }
                buttonsEn();
                break;
            case R.id.btnNext_d:
                changeQuest();
                break;
        }
    }
}
