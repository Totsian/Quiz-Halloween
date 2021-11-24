package com.example.quizhalloween;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizhalloween.OnSelectedButtonListener;
import com.example.quizhalloween.R;

public class FragmentChoose extends Fragment implements View.OnClickListener {

    OnSelectedButtonListener onSelectedButtonListener;
    private Button doctorWho, halloween;

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
        View chooseView = inflater.inflate(R.layout.choose_quiz, container, false);
        doctorWho = chooseView.findViewById(R.id.doctor_who);
        doctorWho.setOnClickListener(this);

        halloween = chooseView.findViewById(R.id.halloween);
        halloween.setOnClickListener(this);

        return chooseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.doctor_who:
                onSelectedButtonListener.onButtonSelected(8);
                break;

            case R.id.halloween:
                onSelectedButtonListener.onButtonSelected(1);
                break;
        }
    }
}
