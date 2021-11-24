package com.example.quizhalloween;

import android.content.Context;
import android.os.Bundle;
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

public class FragmentResult extends Fragment implements View.OnClickListener {

    private TextView resText;
    private Button backBtn;
    OnSelectedButtonListener onSelectedButtonListener;
    int finishResult;

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

    FragmentResult(int finishResult) {
        this.finishResult = finishResult;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View resView = inflater.inflate(R.layout.fragment_result, container, false);

        resText = resView.findViewById(R.id.resText);
        resText.setText("Ваш результат: " + this.finishResult + "/10");

        backBtn = resView.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        return resView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                onSelectedButtonListener.onButtonSelected(4); // меню
                break;
        }
    }
}
