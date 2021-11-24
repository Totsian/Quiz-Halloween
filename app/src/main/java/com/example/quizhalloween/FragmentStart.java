package com.example.quizhalloween;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quizhalloween.OnSelectedButtonListener;
import com.example.quizhalloween.R;

public class FragmentStart extends Fragment implements View.OnClickListener {

    Button startBtn, exitBtn, resultBtn;
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
        View startView = inflater.inflate(R.layout.fragment_start, container, false);

        startBtn = startView.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);

        exitBtn = startView.findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(this);

        resultBtn = startView.findViewById(R.id.resultBtn);
        resultBtn.setOnClickListener(this);

        return startView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                onSelectedButtonListener.onButtonSelected(7); // в игру
                break;
            case R.id.exitBtn:
                onSelectedButtonListener.onButtonSelected(2); // выход
                break;
            case R.id.resultBtn:
                onSelectedButtonListener.onButtonSelected(6); // рейтинг
                break;
        }
    }
}