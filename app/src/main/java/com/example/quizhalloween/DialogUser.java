package com.example.quizhalloween;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.UUID;

public class DialogUser extends AppCompatDialogFragment implements View.OnClickListener {

    private EditText name, surName;
    private Button btnOk, cancel;
    private String nameInf, surNamInf;
    private int userResult;
    OnSelectedButtonListener onSelectedButtonListener;

    public DialogUser(int userResult) {
        this.userResult = userResult;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_user, null);
        setCancelable(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name);
        surName = view.findViewById(R.id.surName);

        btnOk = view.findViewById(R.id.okBtn);
        btnOk.setOnClickListener(this);
        cancel = view.findViewById(R.id.okCancel);
        cancel.setOnClickListener(this);
        getDialog().setTitle(R.string.user_date);
        name.requestFocus();
        surName.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okBtn:
                setCancelable(false);
                dismiss();
                nameInf = name.getText().toString();
                surNamInf = surName.getText().toString();

                FirebaseAdapter firebaseAdapter = new FirebaseAdapter();
                String unId = UUID.randomUUID().toString();
                firebaseAdapter.writeUser(unId, nameInf, surNamInf, userResult);

                OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
                listener.onButtonSelected(3);  // переход к результату пользователя

                break;

            case R.id.okCancel:
                dismiss();
                listener = (OnSelectedButtonListener) getActivity();
                listener.onButtonSelected(3);  // переход к результату пользователя
                break;
        }
    }
}
